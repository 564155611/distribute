package com.fanx.distribute.eventualconsistency.mq.config;

import com.fanx.distribute.eventualconsistency.mq.entity.AccountA;
import com.fanx.distribute.eventualconsistency.mq.mapper.db161.AccountAMapper;
import com.fanx.distribute.eventualconsistency.mq.util.MQTransactionListener;
import com.fanx.distribute.eventualconsistency.mq.util.RocketMQUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
public class RocketMQConfig {

    @Bean(value = "paymentProducer", initMethod = "start", destroyMethod = "shutdown")
    MQProducer paymentProducer(@Qualifier("paymentTransactionListener") TransactionListener paymentTransactionListener) {
        return RocketMQUtil.buildTransactionMQProducer("paymentProducerGroup", paymentTransactionListener, false);
    }

    @Bean("paymentTransactionListener")
    TransactionListener paymentTransactionListener(AccountAMapper accountAMapper) {

        return new MQTransactionListener((msg, args) -> {
            if (! (args instanceof Map<?,?>)) {
                log.error("支付异常,参数不是Map类型:{}",args);
                throw new RuntimeException("支付异常");
            }
            Map<?, ?> parameters = (Map<?,?>) args;
            if (!parameters.containsKey("accountA")|| !parameters.containsKey("amount")) {
                log.error("支付异常,参数需要包含accountA和amount键:{}",args);
                throw new RuntimeException("支付异常");
            }
            AccountA accountA = (AccountA) parameters.get("accountA");
            BigDecimal amount = (BigDecimal) parameters.get("amount");

            BigDecimal balance = accountA.getBalance();
            accountA.setBalance(balance.subtract(amount));
            accountAMapper.updateByPrimaryKey(accountA);
        });
    }

    @Bean(value = "paymentConsumer", initMethod = "start", destroyMethod = "shutdown")
    MQPushConsumer paymentConsumer() {
        return RocketMQUtil.buildConsumer("paymentConsumerGroup", "topic-payment", "*", (msgs, context) -> {
            for (MessageExt msg : msgs) {
                String orderId = msg.getKeys();
                if (orderId == null) {
                    throw new RuntimeException("消息中业务id为空");
                }
                try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
                    HttpPost request = new HttpPost("http://localhost:8080/ordercallback");
                    List<NameValuePair> pairs = Collections.singletonList(new BasicNameValuePair("orderId", orderId));
                    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairs);
                    request.addHeader("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
                    request.setEntity(formEntity);
                    CloseableHttpResponse response = httpClient.execute(request);
                    StatusLine statusLine = response.getStatusLine();
                    HttpEntity entity = response.getEntity();

                    if (statusLine.getStatusCode() != 200 || !"success".equals(EntityUtils.toString(entity))) {
                        throw new RuntimeException("回调接口失败");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException("回调失败");
                }
            }
        }, false);
    }

}
