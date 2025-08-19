package com.DuplicateRemoval.DuplicateRemoval.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
    public class Producer {

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;
        private final Set<String> sentMessages = new HashSet<>();
        public void sendMessageToTopic(String message) {
            if(sentMessages.contains(message))
            {
                System.out.println( "It is duplicate data");
            }
            else {

                kafkaTemplate.send("moni", message);
                sentMessages.add(message);
                sentMessages.forEach(System.out::println);
                System.out.println("Message sent successfully" + message);
            }
        }
    }

