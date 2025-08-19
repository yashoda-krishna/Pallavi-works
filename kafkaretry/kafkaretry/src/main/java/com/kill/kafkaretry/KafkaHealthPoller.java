package com.kill.kafkaretry;


    import org.apache.kafka.clients.admin.AdminClient;
    import org.apache.kafka.clients.admin.AdminClientConfig;
    import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

    import java.util.Properties;
    import java.util.concurrent.ExecutionException;

    @Service
    public class KafkaHealthPoller {
        private int failureCount = 0;
        private static final int MAX_RETRIES = 5;



        @Scheduled(fixedRate = 10000) // every 10 seconds
        public void checkKafkaBrokerStatus() {
            try {
                Properties config = new Properties();
                config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
                 AdminClient adminClient=AdminClient.create(config);
                DescribeClusterResult cluster = adminClient.describeCluster();
                String clusterId = cluster.clusterId().get();

                System.out.println(" Kafka is UP - Cluster ID: " + clusterId);
            } catch (InterruptedException | ExecutionException e) {
                failureCount++;

                System.err.println(" Kafka is DOWN - " + e.getMessage());
                if (failureCount >= MAX_RETRIES) {
                    System.err.println(" Kafka unreachable after 5 tries. Shutting down.");



                }
            }
            }




    }


