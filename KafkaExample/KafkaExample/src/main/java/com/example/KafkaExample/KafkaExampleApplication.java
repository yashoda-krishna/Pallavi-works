package com.example.KafkaExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaExampleApplication.class, args);
        System.out.println("Kafka Application Started");
    }
}
