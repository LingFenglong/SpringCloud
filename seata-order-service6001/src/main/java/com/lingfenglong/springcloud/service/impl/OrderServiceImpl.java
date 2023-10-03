package com.lingfenglong.springcloud.service.impl;

import com.lingfenglong.springcloud.domain.Order;
import com.lingfenglong.springcloud.mapper.OrderMapper;
import com.lingfenglong.springcloud.service.AccountService;
import com.lingfenglong.springcloud.service.OrderService;
import com.lingfenglong.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@GlobalTransactional
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final StorageService storageService;
    private final AccountService accountService;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, StorageService storageService, AccountService accountService) {
        this.orderMapper = orderMapper;
        this.storageService = storageService;
        this.accountService = accountService;
    }

    @Override
    public void pay(Order order) {
        log.info("下订单开始");
        log.info("order = {}", order);

        // 创建订单
        log.info("正在创建订单");
        orderMapper.create(order);
        log.info("order = {}", order);
        log.info("订单创建成功");

        // 扣减库存
        log.info("扣减库存开始");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("扣减库存成功");

        // 扣减余额
        log.info("扣减余额开始");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("扣减余额成功");

        // 修改订单状态
        log.info("修改订单状态开始");
        orderMapper.update(order.getId(), 0);
        log.info("修改订单状态成功");

        log.info("下订单成功");
    }
}














