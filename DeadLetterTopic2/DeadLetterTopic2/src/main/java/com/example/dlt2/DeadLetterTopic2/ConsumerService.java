package com.example.dlt2.DeadLetterTopic2;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "${app.topic.main}", groupId = "demo-group")
    public void consume(String message) {
        System.out.println("Consumed: " + message);
        if (message.contains("fail")) {
            throw new RuntimeException("Simulated failure: " + message);
        }
    }
}