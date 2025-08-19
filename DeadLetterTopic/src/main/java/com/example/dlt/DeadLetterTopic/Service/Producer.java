package com.example.dlt.DeadLetterTopic.Service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    @Autowired
    private  KafkaTemplate<String, String> Kafkatemplste;
    public void SendMessage(String message)
    {
        Kafkatemplste.send("Example",message);
    }

}
