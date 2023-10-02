package com.lingfenglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.sql.Driver;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Order85Application {
    public static void main(String[] args) {
        SpringApplication.run(Order85Application.class, args);

    }
}
