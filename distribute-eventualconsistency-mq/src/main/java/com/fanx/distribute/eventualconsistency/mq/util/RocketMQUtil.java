package com.fanx.distribute.eventualconsistency.mq.util;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class RocketMQUtil {
    public static DefaultMQProducer buildMQProducer(String groupName) {
        return buildMQProducer(groupName, true);
    }

    public static DefaultMQProducer buildMQProducer(String groupName, boolean start) {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr("192.168.12.11:9876");
        if (start) {
            start(producer);
        }
        return producer;
    }

    public static TransactionMQProducer buildTransactionMQProducer(String groupName,
                                                                   TransactionListener transactionListener) {
        return buildTransactionMQProducer(groupName,  transactionListener, true);
    }

    public static TransactionMQProducer buildTransactionMQProducer(String groupName,
                                                                   TransactionListener transactionListener, boolean start) {
        TransactionMQProducer producer = new TransactionMQProducer(groupName);
        producer.setNamesrvAddr("192.168.12.11:9876");
        //设置自定义线程池来处理后面的检查请求
        ThreadPoolExecutor executorService =
                new ThreadPoolExecutor(2, 5,
                        100, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(2000),
                        new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                Thread thread = new Thread(r);
                                thread.setName(groupName + "-transaction-msg-check-thread");
                                return thread;
                            }
                        });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        if (start) {
            start(producer);
        }
        return producer;
    }


    public static DefaultMQPushConsumer buildConsumer(String groupName, String topic, String tags, BiConsumer<List<MessageExt>, ConsumeConcurrentlyContext> consumer) {
        return buildConsumer(groupName, topic, tags, consumer, true);
    }

    public static DefaultMQPushConsumer buildConsumer(String groupName, String topic, String tags, BiConsumer<List<MessageExt>, ConsumeConcurrentlyContext> consumer, boolean start) {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(groupName);
        pushConsumer.setNamesrvAddr("192.168.12.11:9876");
        try {
            pushConsumer.subscribe(topic, tags);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        //注册回调实现类来处理从broker拉取回来的消息
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try {
                    consumer.accept(msgs, context);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        if (start) {
            start(pushConsumer);
        }
        return pushConsumer;
    }

    private static void start(DefaultMQProducer producer) {
        try {
            producer.start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                producer.shutdown();
            }));
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    private static void start(DefaultMQPushConsumer pushConsumer) {
        // 启动消费者实例
        try {
            pushConsumer.start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                pushConsumer.shutdown();
            }));
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
