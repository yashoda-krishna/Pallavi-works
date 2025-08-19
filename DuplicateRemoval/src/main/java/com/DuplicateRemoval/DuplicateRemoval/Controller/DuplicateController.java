package com.DuplicateRemoval.DuplicateRemoval.Controller;

import com.DuplicateRemoval.DuplicateRemoval.Service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/basic")

    public class DuplicateController {
        @Autowired
        private Producer producer;

        @GetMapping
        public String getMessage(@RequestParam("msg") String message) {
            producer.sendMessageToTopic(message);
            return "Message sent to Kafka topic successfully: " + message;


        }
    }

