package com.lingfenglong.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lingfenglong.springcloud.entity.CommonResult;
import com.lingfenglong.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;

public interface PaymentService extends IService<Payment> {
    int create(Payment payment);

    Payment getPaymentById(Long id);

    CommonResult<Integer> fineTimeout();

}
