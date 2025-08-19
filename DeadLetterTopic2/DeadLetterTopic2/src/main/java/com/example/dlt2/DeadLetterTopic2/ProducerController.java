package com.example.dlt2.DeadLetterTopic2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produce")
public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.main}")
    private String topic;

    public ProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/{message}")
    public String sendMessage(@PathVariable String message) {
        kafkaTemplate.send(topic, message);
        return "Message sent: " + message;
    }
}