package com.sample2.Sample2;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Send {
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC1 = "topic1";
    private static final String TOPIC2 = "Krishna";
    private static final Set<String> sentMessages = new HashSet<>();

    public static void main(String[] args) throws Exception {
        // Determine which topic to use
        String targetTopic = topicExists(TOPIC1) ? TOPIC1 : TOPIC2;
        System.out.println("✅ Using topic: " + targetTopic);

        try (
                KafkaProducer<String, String> producer = createProducer();
                KafkaConsumer<String, String> consumer = createConsumer(targetTopic);
                Scanner scanner = new Scanner(System.in)
        ) {
            // Start consumer thread
            new Thread(() -> {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("📥 " + record.value());
                    }
                }
            }).start();

            // Read user input and send messages
            System.out.println("💬 Enter messages (type 'exit' to quit):");
            while (true) {
                String msg = scanner.nextLine().trim();
                if (msg.equalsIgnoreCase("exit")) break;

                if (sentMessages.add(msg)) {
                    producer.send(new ProducerRecord<>(targetTopic, msg));
                    System.out.println("✅ Sent: " + msg);
                } else {
                    System.out.println("⚠ Duplicate skipped: " + msg);
                }
            }
        }

        System.out.println("👋 Exiting...");
    }

    // Check if a topic exists on the Kafka cluster
    private static boolean topicExists(String topic) throws ExecutionException, InterruptedException {
        try (AdminClient admin = AdminClient.create(Map.of(
                "bootstrap.servers", BOOTSTRAP_SERVERS
        ))) {
            return admin.listTopics().names().get().contains(topic);
        }
    }

    // Create Kafka producer
    private static KafkaProducer<String, String> createProducer() {
        return new KafkaProducer<>(Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()
        ));
    }

    // Create Kafka consumer
    private static KafkaConsumer<String, String> createConsumer(String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                ConsumerConfig.GROUP_ID_CONFIG, "simple-console-consumer",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"
        ));
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }
}
