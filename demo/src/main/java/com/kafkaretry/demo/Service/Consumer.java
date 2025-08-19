package com.kafkaretry.demo.Service;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

    @Service
    public class Consumer {

        private final AtomicLong lastReceivedTimestamp = new AtomicLong(System.currentTimeMillis());
        private final AtomicInteger consumerFailureCount = new AtomicInteger(0);

        public long getLastReceivedTimestamp() {
            return lastReceivedTimestamp.get();
        }

        public int getFailureCount() {
            return consumerFailureCount.get();
        }

        public void resetFailureCount() {
            consumerFailureCount.set(0);
        }

        @KafkaListener(topics = "Pallavi", groupId = "Pallavi_group")
        public void listenToTopic(String receivedMessage) {
            try {
                System.out.println("Consumer received: " + receivedMessage);
                lastReceivedTimestamp.set(System.currentTimeMillis());
                resetFailureCount();
            } catch (Exception e) {
                int count = consumerFailureCount.incrementAndGet();
                System.err.println("Consumer failed (" + count + "): " + e.getMessage());
            }
        }
    }


