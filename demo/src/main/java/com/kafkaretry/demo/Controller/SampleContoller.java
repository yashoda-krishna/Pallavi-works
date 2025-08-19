package com.kafkaretry.demo.Controller;




import com.kafkaretry.demo.Service.Consumer;
import com.kafkaretry.demo.Service.Producer;
import com.kafkaretry.demo.Service.ShutdownManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class SampleContoller {


    private static final int MAX_RETRIES = 5;
        private static final long MAX_CONSUMER_DELAY_MS = 15000;

        @Autowired
        private Producer producer;

        @Autowired
        private Consumer consumer;

        @Autowired
        private ShutdownManager shutdownManager;
        @GetMapping
        @Scheduled(fixedRate = 10000)
        public void monitorHealth() {
            boolean consumerTimedOut = (System.currentTimeMillis() - consumer.getLastReceivedTimestamp()) > MAX_CONSUMER_DELAY_MS;
            if (consumerTimedOut) {
                consumer.getFailureCount(); // Keep track by allowing listener to increase it.
            }

            int producerFailures = producer.getFailureCount();
            int consumerFailures = consumer.getFailureCount();

            System.out.println("Health check -> Producer failures: " + producerFailures + ", Consumer failures: " + consumerFailures);

            if (producerFailures >= MAX_RETRIES || consumerFailures >= MAX_RETRIES) {
                System.err.println("Producer or Consumer failed 5 times. Shutting down...");
                shutdownManager.shutdownApp();
            }
        }
    }

//import java.util.concurrent.TimeUnit;
//
//public class KafkaConsumerRetryExit {
//
//    private static final String TOPIC_NAME = "my-topic";
//    private static final int MAX_RETRIES = 5;
//    private static final long RETRY_INTERVAL_MS = 2000;
//
//import java.util.Properties;
//    public static void main(String[] args) {
//
//        String broker = "local-host:9092"; // Corrected 'local-host' to 'localhost'
//        Properties props = new Properties();
//        props.put("bootstrap.servers", broker);
//        props.put("group.id", "retry-group");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//
//        if (!isKafkaAvailable(props)) {
//            System.err.println("❌ Kafka is not available. Exiting.");
//            System.exit(1);
//        }
//
//        for (int i = 1; i <= MAX_RETRIES; i++) {
//            try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
//                consumer.subscribe(Collections.singletonList(TOPIC_NAME));
//                System.out.println("✅ Connected to Kafka and subscribed to topic: " + TOPIC_NAME);
//                return;
//            } catch (Exception e) {
//                System.err.printf("⚠️ Retry %d/%d failed: %s%n", i, MAX_RETRIES, e.getMessage());
//                if (i == MAX_RETRIES) {
//                    System.err.println("❌ Max retries reached. Exiting.");
//                    System.exit(1);
//                }
//                try {
//                    Thread.sleep(RETRY_INTERVAL_MS * i);
//                } catch (InterruptedException ie) {
//                    System.err.println("❌ Retry interrupted. Exiting.");
//                    System.exit(1);
//                }
//            }
//        }
//    }
//
//    private static boolean isKafkaAvailable(Properties props) {
//        try (AdminClient admin = AdminClient.create(props)) {
//            String clusterId = admin.describeCluster().clusterId().get();
//            System.out.println("✅ Kafka is available. Cluster ID: " + clusterId);
//            return true;
//        } catch (Exception e) {
//            System.err.println("❌ Kafka check failed: " + e.getMessage());
//            return false;
//        }
//    }
//}
//

