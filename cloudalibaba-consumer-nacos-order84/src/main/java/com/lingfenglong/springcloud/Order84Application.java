package com.lingfenglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Order84Application {
    public static void main(String[] args) {
        SpringApplication.run(Order84Application.class, args);
    }
}
