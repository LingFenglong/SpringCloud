package com.lingfenglong.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/consumer")
@RefreshScope
public class OrderController {
    private final String PAYMENT_URL = "http://cloudalibaba-provider-payment";
    private final RestTemplate restTemplate;

    @Value("${lingfenglong.config}")
    private String config;

    @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payment/info")
    public String info() {
        log.info(config);
        return restTemplate.getForObject(PAYMENT_URL + "/payment/info", String.class);
    }
}
