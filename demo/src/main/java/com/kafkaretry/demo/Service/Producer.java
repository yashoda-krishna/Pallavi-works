package com.kafkaretry.demo.Service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

    @Service
    public class Producer {

        private static final String TOPIC = "Pallavi";
        private final KafkaTemplate<String, String> kafkaTemplate;
        private final AtomicInteger producerFailureCount = new AtomicInteger(0);

        @Autowired
        private ShutdownManager shutdownManager;

        @Autowired
        public Producer(KafkaTemplate<String, String> kafkaTemplate) {
            this.kafkaTemplate = kafkaTemplate;
        }

        public int getFailureCount() {
            return producerFailureCount.get();
        }

        public void resetFailureCount() {
            producerFailureCount.set(0);
        }

        public void sendMessageToTopic(String message) {
            try {
                kafkaTemplate.send(TOPIC, message);
                kafkaTemplate.flush();
                resetFailureCount();
                System.out.println("Producer sent message: " + message);
            } catch (Exception e) {
                int count = producerFailureCount.incrementAndGet();
                System.err.println("Producer failed (" + count + "): " + e.getMessage());
            }
        }

        @Scheduled(fixedRate = 5000)
        public void sendHealthCheck() {
            sendMessageToTopic("health_check");
        }
    }
//import org.apache.kafka.clients.admin.AdminClient;
//
//    import org.apache.kafka.clients.admin.AdminClientConfig;
//
//    import org.apache.kafka.clients.admin.DescribeClusterResult;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.scheduling.annotation.Scheduled;
//
//import org.springframework.stereotype.Service;
//
//    import java.util.Properties;
//
//    import java.util.concurrent.ExecutionException;
//
//@Service
//
//public class KafkaHealthPoller {
//
//    private int failureCount = 0;
//
//    private static final int MAX_RETRIES = 5;
//
//
//    @Scheduled(fixedRate = 10000) // every 10 seconds
//
//    public void checkKafkaBrokerStatus() {
//
//        try {
//
//            Properties config = new Properties();
//
//            config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//
//            AdminClient adminClient=AdminClient.create(config);
//
//            DescribeClusterResult cluster = adminClient.describeCluster();
//
//            String clusterId = cluster.clusterId().get();
//
//            System.out.println(" Kafka is UP - Cluster ID: " + clusterId);
//
//        } catch (InterruptedException | ExecutionException e) {
//
//            failureCount++;
//
//            System.err.println(" Kafka is DOWN - " + e.getMessage());
//
//            if (failureCount >= MAX_RETRIES) {
//
//                System.err.println(" Kafka unreachable after 5 tries. Shutting down.");
//
//
//            }
//
//        }
//
//    }
//
//
//
//}
