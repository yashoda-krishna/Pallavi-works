package com.example.KafkaExample.util;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.common.KafkaException;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaHEalthChecker {
    private final AdminClient adminClient;


    public KafkaHEalthChecker(String bootstrapServers) {
        Properties config = new Properties();
        config.put("bootstrap.servers", bootstrapServers);
        this.adminClient = AdminClient.create(config);
    }

    public  boolean isKafkaUp() {
        try {
            DescribeClusterResult result = adminClient.describeCluster();
            result.clusterId().get();
            return true;
        } catch (InterruptedException | ExecutionException | KafkaException e) {
            return false;
        }
    }
}
