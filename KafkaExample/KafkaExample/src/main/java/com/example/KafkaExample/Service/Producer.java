package com.example.KafkaExample.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageToTopic(String message) {

        kafkaTemplate.send("Sanjay", 1,"key","value");
    }
}
