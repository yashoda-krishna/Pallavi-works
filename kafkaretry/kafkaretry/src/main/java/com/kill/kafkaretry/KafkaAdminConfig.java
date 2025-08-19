//package com.kill.kafkaretry;
//
//
//    import org.apache.kafka.clients.admin.AdminClient;
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
//    @Configuration
//    public class KafkaAdminConfig {
//
//        @Bean
//        public AdminClient kafkaAdminClient() {
//            Properties config = new Properties();
//            config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//            return AdminClient.create(config);
//        }
//    }
//
//
