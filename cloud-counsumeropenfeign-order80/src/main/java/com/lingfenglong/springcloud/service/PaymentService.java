package com.lingfenglong.springcloud.service;

import com.lingfenglong.springcloud.entity.CommonResult;
import com.lingfenglong.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(value = "cloud-payment-service", path = "/payment")
public interface PaymentService {
    @GetMapping("/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @PostMapping("/create")
    CommonResult<?> create(@RequestBody Payment payment);

    @GetMapping("/feign/timeout")
    CommonResult<Integer> fineTimeout();
}
