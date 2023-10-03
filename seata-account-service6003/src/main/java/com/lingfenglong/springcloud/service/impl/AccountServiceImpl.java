package com.lingfenglong.springcloud.service.impl;

import com.lingfenglong.springcloud.mapper.AccountMapper;
import com.lingfenglong.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public void decrease(Long userId, BigDecimal money) {
        accountMapper.decrease(userId, money);
    }
}
