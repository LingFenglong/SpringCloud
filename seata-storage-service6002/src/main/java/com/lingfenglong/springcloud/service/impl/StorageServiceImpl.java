package com.lingfenglong.springcloud.service.impl;

import com.lingfenglong.springcloud.mapper.StorageMapper;
import com.lingfenglong.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageMapper storageMapper;

    @Autowired
    public StorageServiceImpl(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }

    @Override
    public void decrease(Long id, Integer count) {
        storageMapper.decrease(id, count);
    }
}
