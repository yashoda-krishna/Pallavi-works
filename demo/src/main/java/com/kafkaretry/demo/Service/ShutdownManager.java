package com.kafkaretry.demo.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

    @Component
    public class ShutdownManager {

        @Autowired
        private ApplicationContext context;

        public void shutdownApp() {
            int exitCode = SpringApplication.exit(context, () -> 1);
            System.exit(exitCode);
        }
    }



