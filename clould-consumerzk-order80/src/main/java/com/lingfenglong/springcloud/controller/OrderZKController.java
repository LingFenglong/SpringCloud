package com.lingfenglong.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderZKController {
    public static final String PAYMENT_URL = "http://cloud-provider-payment";

    private final RestTemplate restTemplate;

    @Autowired
    public OrderZKController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payment/zk")
    public String paymentZK() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
    }
}
