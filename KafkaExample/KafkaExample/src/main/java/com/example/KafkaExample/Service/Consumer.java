package com.example.KafkaExample.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "Sanjay", groupId = "sanjay_group")
    public void listenToTopic(String receivedMessage) {
        System.out.println("Message received: " + receivedMessage);
    }
}
