package com.lingfenglong.springcloud.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MQConsumer8802Application {
    public static void main(String[] args) {
        SpringApplication.run(MQConsumer8802Application.class, args);
    }
}
