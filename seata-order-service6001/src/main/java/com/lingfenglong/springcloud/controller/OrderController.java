package com.lingfenglong.springcloud.controller;

import com.lingfenglong.springcloud.common.CommonResult;
import com.lingfenglong.springcloud.domain.Order;
import com.lingfenglong.springcloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/pay")
    public CommonResult<Order> pay(Order order) {
        orderService.pay(order);

        return new CommonResult<>(200, "支付成功", order);
    }
}
