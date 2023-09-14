package com.lingfenglong.springcloud.controller;

import com.lingfenglong.springcloud.entity.CommonResult;
import com.lingfenglong.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/customer")
public class OrderController {
    private final String PAYMENT_SERVICE_URL = "http://CLOUD-PAYMENT-SERVICE";
   private final RestTemplate restTemplate;

   @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_SERVICE_URL + "/payment/get/{id}", CommonResult.class, id);
    }

    @PostMapping("/payment/create")
    public CommonResult<?> createPayment(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/payment/create", payment, CommonResult.class);
    }
}
