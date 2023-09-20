package com.lingfenglong.springcloud.controller;

import com.lingfenglong.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customer")
public class OrderController {
    private final PaymentService paymentService;

    @Autowired
    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_OK(id);
    }

    @HystrixCommand(fallbackMethod = "fallbackTimeout")
    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_TimeOut(id);
    }

    public String fallbackTimeout() {
        return "This is the fall back page";
    }
}
