package com.Topic.TopicExistence.Service;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collections;

    import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
@Service
    public class Consumer{

        private static final String MAIN_TOPIC = "my-main-topic";
        private static final String DLT_TOPIC = "my-main-topic-DLT";
        private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    @KafkaListener(topics = "Krishna",groupId = "krishan_group")
        public static void main(String[] args) {
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            KafkaMessageProducer dltProducer = new KafkaMessageProducer(BOOTSTRAP_SERVERS);

            consumer.subscribe(Collections.singletonList(MAIN_TOPIC));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : records) {
                    try {
                        // Simulate processing
                        System.out.println("Processing message: " + record.value());

                        if (record.value().contains("fail")) {
                            throw new RuntimeException("Simulated processing error");
                        }

                        // Message processed successfully

                    } catch (Exception e) {
                        System.err.println("Error processing message: " + e.getMessage());
                        // Send to DLT
                        dltProducer.sendMessage(DLT_TOPIC, record.key(), record.value());
                    }
                }
            }

        }
    }


