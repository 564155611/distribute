package com.fanx.distribute.eventualconsistency.mq.util;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class MQTransactionListener implements TransactionListener {
    private final Map<String, LocalTransactionState> localTrans = new ConcurrentHashMap<>();
    private final BiConsumer<Message, Object> executor;
    @Autowired
    private ApplicationContext applicationContext;

    public MQTransactionListener(BiConsumer<Message, Object> executor) {
        this.executor = executor;
    }

    @Transactional(transactionManager = "tm161")
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            localTrans.put(msg.getTransactionId(), LocalTransactionState.UNKNOW);
            executor.accept(msg, arg);
            applicationContext.publishEvent(msg);
        } catch (Exception e) {
            e.printStackTrace();
            localTrans.put(msg.getTransactionId(), LocalTransactionState.ROLLBACK_MESSAGE);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return localTrans.get(msg.getTransactionId());
    }

    @TransactionalEventListener(classes = Message.class, phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterPaymentRollback(Message message) {
        String transactionId = message.getTransactionId();
        localTrans.put(transactionId, LocalTransactionState.ROLLBACK_MESSAGE);
    }

    @TransactionalEventListener(classes = Message.class, phase = TransactionPhase.AFTER_COMMIT)
    public void afterPaymentCommit(Message message) {
        String transactionId = message.getTransactionId();
        localTrans.put(transactionId, LocalTransactionState.COMMIT_MESSAGE);
    }
}

