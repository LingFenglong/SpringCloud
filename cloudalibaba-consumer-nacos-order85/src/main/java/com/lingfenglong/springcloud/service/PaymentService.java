package com.lingfenglong.springcloud.service;

import com.lingfenglong.springcloud.handler.fallback.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-provider-payment", path = "/payment", fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping(value = "/get/{id}")
    String paymentSQL(@PathVariable("id") Long id);
}
