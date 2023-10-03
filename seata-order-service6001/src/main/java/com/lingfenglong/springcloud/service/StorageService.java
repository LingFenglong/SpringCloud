package com.lingfenglong.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "storage-service", path = "/storage")
public interface StorageService {
    @GetMapping("/decrease")
    String decrease(@RequestParam("id") Long id, @RequestParam("count") Integer count);
}
