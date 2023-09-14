package com.lingfenglong.springcloud.controller;

import com.lingfenglong.springcloud.entity.CommonResult;
import com.lingfenglong.springcloud.entity.Payment;
import com.lingfenglong.springcloud.service.PaymentService;
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

}
