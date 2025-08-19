package com.example.dlt.DeadLetterTopic.Controller;

import com.example.dlt.DeadLetterTopic.Service.Consumer;
import com.example.dlt.DeadLetterTopic.Service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {

      @Autowired
     Producer producer;

      @GetMapping
      public void ex(@RequestParam("msg") String message)
      {
            producer.SendMessage(message);
            System.out.println("message sent");
      }


}
