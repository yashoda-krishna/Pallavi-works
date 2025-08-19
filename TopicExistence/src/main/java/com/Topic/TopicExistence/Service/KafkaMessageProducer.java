package com.Topic.TopicExistence.Service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class KafkaMessageProducer {

    private final KafkaProducer<String, String> producer;

    public KafkaMessageProducer(@Value("localhost:9092") String bootstrapServers) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
    }

    public void sendMessage(String topic,String key,String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,key,message);
        System.out.println("==========================="+" "+message);
        producer.send(record, (RecordMetadata metadata, Exception exception) -> {
            if (exception != null) {
                System.err.println("Failed to send message to " + topic + ": " + exception.getMessage());
            } else {
                System.out.println(" Message sent to topic: " + topic + ", offset: " + metadata.offset());
            }
        });
    }

    public void close() {
        producer.close();
    }
}
