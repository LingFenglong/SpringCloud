package com.lingfenglong.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lingfenglong.springcloud.entity.Payment;
import com.lingfenglong.springcloud.mapper.PaymentMapper;
import com.lingfenglong.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
    @Override
    public int create(Payment payment) {
        return baseMapper.insert(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return baseMapper.selectById(id);
    }
}
