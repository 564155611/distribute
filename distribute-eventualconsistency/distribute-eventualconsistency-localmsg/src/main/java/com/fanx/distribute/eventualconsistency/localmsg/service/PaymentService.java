package com.fanx.distribute.eventualconsistency.localmsg.service;

import com.fanx.distribute.eventualconsistency.localmsg.entity.AccountA;
import com.fanx.distribute.eventualconsistency.localmsg.entity.PaymentMsg;
import com.fanx.distribute.eventualconsistency.localmsg.entity.PaymentMsgExample;
import com.fanx.distribute.eventualconsistency.localmsg.mapper.db161.AccountAMapper;
import com.fanx.distribute.eventualconsistency.localmsg.mapper.db161.PaymentMsgMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    AccountAMapper accountAMapper;
    @Autowired
    PaymentMsgMapper paymentMsgMapper;

    /**
     * @param userId
     * @param orderId
     * @param amount
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
        accountA.setBalance(balance.subtract(amount));
        accountAMapper.updateByPrimaryKey(accountA);

        PaymentMsg paymentMsg = new PaymentMsg();
        paymentMsg.setOrderId(orderId);
        paymentMsg.setStatus(0);
        paymentMsg.setFailureCount(0);
        paymentMsg.setCreateTime(new Date());
        paymentMsg.setCreateUser(userId);
        paymentMsg.setUpdateTime(new Date());
        paymentMsg.setUpdateUser(userId);
        paymentMsgMapper.insertSelective(paymentMsg);
        return 0;
    }

    @Transactional(transactionManager = "tm161")
    @Scheduled(cron = "0/10 * * * * ?")
    public void orderNotify() {
        PaymentMsgExample paymentMsgExample = new PaymentMsgExample();
        PaymentMsgExample.Criteria criteria = paymentMsgExample.createCriteria();
        criteria.andStatusEqualTo(0);
        List<PaymentMsg> paymentMsgs = paymentMsgMapper.selectByExample(paymentMsgExample);
        if (paymentMsgs == null || paymentMsgs.size() == 0) {
            return;
        }
        paymentMsgs.forEach(paymentMsg -> {

            try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
                HttpPost request = new HttpPost("http://localhost:8080/ordercallback");
                List<NameValuePair> pairs = Arrays.asList(new BasicNameValuePair("orderId", "10010"));
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairs);
                request.addHeader("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
                request.setEntity(formEntity);
                CloseableHttpResponse response = httpClient.execute(request);
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();

                if (statusLine.getStatusCode() != 200 || !"success".equals(EntityUtils.toString(entity))) {
                    Integer failureCount = paymentMsg.getFailureCount();
                    failureCount++;
                    if (failureCount > 5) {
                        paymentMsg.setStatus(2);
                    } else {
                        paymentMsg.setFailureCount(failureCount);
                    }
                } else {
                    paymentMsg.setStatus(1);
                }
                paymentMsg.setUpdateUser(0);
                paymentMsg.setUpdateTime(new Date());
                paymentMsgMapper.updateByPrimaryKey(paymentMsg);
            } catch (Exception ex) {
                throw new RuntimeException("回调失败");
            }
        });
    }

}
