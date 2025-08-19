package com.example.dlt.DeadLetterTopic.Service;



import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 1000),
            dltTopicSuffix = ".DLT",
            autoCreateTopics = "true"
    )
    @KafkaListener(topics = "Example", groupId = "my-group")
    public void listen(String message) {
        System.out.println(" Consumed message: " + message);
        if (message.contains("fail")) {
            throw new RuntimeException("Failed to process message: " + message);
        }
    }

    @DltHandler
    public void handleDlq(String message) {
        
        System.err.println("Message sent to DLT: " + message);
    }
}
