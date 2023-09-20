package com.lingfenglong.springcloud.controller;

import com.lingfenglong.springcloud.entity.CommonResult;
import com.lingfenglong.springcloud.entity.Payment;
import com.lingfenglong.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public CommonResult<?> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***插入操作结果：" + result);

        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功" + port, result);
        } else {
            return new CommonResult<>(444, "插入数据库失败" + port);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果：" + payment);

        if (payment != null) {
            return new CommonResult<>(200, "查询成功" + port, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录" + port);
        }
    }

    @GetMapping("/lb")
    public CommonResult<Payment> lb() {
        return new CommonResult<>(2000, port.toString());
    }

    @GetMapping("/feign/timeout")
    public CommonResult<Integer> fineTimeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new CommonResult<>(200, port.toString());
    }

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return "paymentInfo --- OK  " + id;
    }

    @HystrixCommand(fallbackMethod = "fallbackTimeout", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @GetMapping("/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "paymentInfo --- TIMEOUT  " + id;
    }

    public String fallbackTimeout() {
        return "8001 --- Timeout!";
    }

    @GetMapping("/predicate/test")
    public String predicate() {
        return "你好";
    }
}