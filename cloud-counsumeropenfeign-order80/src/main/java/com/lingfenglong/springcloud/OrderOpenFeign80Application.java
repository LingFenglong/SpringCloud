package com.lingfenglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderOpenFeign80Application {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeign80Application.class, args);
    }
}
