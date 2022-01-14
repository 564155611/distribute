package com.fanx.distribute.eventualconsistency.mq.event;

import com.fanx.distribute.eventualconsistency.mq.entity.AccountA;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.common.message.Message;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class PaymentEvent {
    private AccountA accountA;
    private Integer orderId;
    private BigDecimal amount;
    private Message message;
}
