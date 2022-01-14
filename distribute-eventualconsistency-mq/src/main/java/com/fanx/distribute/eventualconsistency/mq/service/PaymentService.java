package com.fanx.distribute.eventualconsistency.mq.service;

import com.fanx.distribute.eventualconsistency.mq.entity.AccountA;
import com.fanx.distribute.eventualconsistency.mq.mapper.db161.AccountAMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PaymentService {
    @Autowired
    AccountAMapper accountAMapper;
    @Resource(name = "paymentProducer")
    MQProducer producer;

    /**
     * @param userId    用户id
     * @param orderId   订单id
     * @param amount    支付金额
     * @return 1:用户不存在 2:余额不足
     */
    @Transactional(transactionManager = "tm161")
    public int pay(Integer userId, Integer orderId, BigDecimal amount) {
        AccountA accountA = accountAMapper.selectByPrimaryKey(userId);
        if (accountA == null) {
            return 1;
        }
        BigDecimal balance = accountA.getBalance();
        if (amount == null || amount.compareTo(balance) > 0) {
            return 2;
        }
        Message message = new Message();
        message.setTopic("topic-payment");
        message.setKeys(orderId + "");
        message.setBody("发送成功".getBytes(StandardCharsets.UTF_8));
        Map<String,Object> args = new HashMap<>();
        args.put("accountA", accountA);
        args.put("orderId", orderId);
        args.put("amount", amount);
        try {
            TransactionSendResult sendResult = producer.sendMessageInTransaction(message, args);
            if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                log.info("支付成功,msgId:{}",sendResult.getMsgId());
            }else {
                log.error("发送成功但是同步失败,msgId:{}",sendResult.getMsgId());
            }
        } catch (MQClientException e) {
            e.printStackTrace();
            log.error("连接失败");
        }

        return 0;
    }


}
