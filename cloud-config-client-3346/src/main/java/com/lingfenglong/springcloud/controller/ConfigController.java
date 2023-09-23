package com.lingfenglong.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
    @Value("${server.port}")
    private Integer port;

    @Value("${config.info}")
    private String configInfo;

    @Value("${config.version}")
    private String version;

    @GetMapping("/config")
    public String config() {
        return port + "\r\n" + configInfo + "\r\n" + version;
    }
}