package com.example.dlt2.DeadLetterTopic2;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DltConsumerService {

    @KafkaListener(topics = "${app.topic.dlt}", groupId = "demo-group-dlt")
    public void consumeFromDlt(String message) {
        System.out.println("⚠ Received in DLT: " + message);
    }
}