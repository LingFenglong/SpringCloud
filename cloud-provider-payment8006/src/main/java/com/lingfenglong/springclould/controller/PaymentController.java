package com.lingfenglong.springclould.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/consul")
    public String getPayment() {
        return "spring cloud with consul: " + serverPort + "\t" + UUID.randomUUID();
    }
}
