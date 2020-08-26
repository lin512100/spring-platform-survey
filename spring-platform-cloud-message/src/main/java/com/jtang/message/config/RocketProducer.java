package com.jtang.message.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Scanner;

public class RocketProducer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr("129.204.1.173:9876");
        producer.setInstanceName("rmq-instance");
        producer.start();
        try {
            Message message = new Message("ACCOUNT", "demo-tag", "这是一条测试消息".getBytes());
            producer.send(message);

            while (true) {
                String text = new Scanner(System.in).next();
                Message msg = new Message("ACCOUNT",// topic
                        "demo-tag",// tag
                        text.getBytes() // body
                );
                SendResult sendResult = producer.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
