package com.kill.kafkaretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaretryApplication {




	public static void main(String[] args) {
        KafkaHealthPoller  kafkaHealthPoller=new KafkaHealthPoller();
        kafkaHealthPoller.checkKafkaBrokerStatus();
		SpringApplication.run(KafkaretryApplication.class, args);
	}

}
