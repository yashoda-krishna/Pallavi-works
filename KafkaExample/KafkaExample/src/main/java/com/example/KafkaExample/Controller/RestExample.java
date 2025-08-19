package com.example.KafkaExample.Controller;

import com.example.KafkaExample.Service.Producer;
import com.example.KafkaExample.util.KafkaHEalthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/example")
public class RestExample {


    KafkaHEalthChecker kafka=new KafkaHEalthChecker("localhost:9092");



    @Autowired
    private Producer producer;

    @GetMapping
    public String getMessage(@RequestParam("msg") String message){
        if(kafka.isKafkaUp()) {
            producer.sendMessageToTopic(message);
            return "Message sent to Kafka topic successfully: " + message;
        }
        else {
            return "Kafka is down. Message not sent.";
        }

    }
}
