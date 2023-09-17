package com.lingfenglong.springcloud.controller;

import com.lingfenglong.springcloud.entity.CommonResult;
import com.lingfenglong.springcloud.entity.Payment;
import com.lingfenglong.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Slf4j
public class OrderController {
    private final PaymentService paymentService;

    @Autowired
    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping("/payment/create")
    public CommonResult<?> create(@RequestBody Payment payment) {
        return paymentService.create(payment);
    }

    @GetMapping("/payment/feign/timeout")
    public CommonResult<Integer> fineTimeout() {
        return paymentService.fineTimeout();
    }
}
