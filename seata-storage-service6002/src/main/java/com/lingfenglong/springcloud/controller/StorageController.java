package com.lingfenglong.springcloud.controller;

import com.lingfenglong.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/decrease")
    public String decrease(@RequestParam("id") Long id, @RequestParam("count") Integer count) {
        storageService.decrease(id, count);
        log.info("扣减库存成功");
        return "扣减库存成功 id = " + id + ", count = " + count;
    }
}
