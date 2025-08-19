package com.DuplicateRemoval.DuplicateRemoval.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topics = "moni", groupId = "moni_group")
    public void listenToTopic(String receivedMessage) {
        System.out.println("Message received: " + receivedMessage);
    }
}
