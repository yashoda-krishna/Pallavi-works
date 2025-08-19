package com.Topic.TopicExistence.ExampleContoller;

import com.Topic.TopicExistence.Service.KafkaMessageProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExController {


    @Autowired
    KafkaMessageProducer producer;
    @GetMapping("/basic")
    public void Check(@RequestParam("msg") String message)
    {
        producer.sendMessage("Krishna","2", message);
    }



}
