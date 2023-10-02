package com.lingfenglong.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lingfenglong.springcloud.blockhandler.MyBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {
    @GetMapping("/resource")
    @SentinelResource(value = "resource", blockHandler = "myBlockHandler")
    public String resource() {
        return "resource";
    }

    public String myBlockHandler(BlockException e) {
        return "Here is Block Handler";
    }
    public String fallbackHandler(Throwable e) {
        return "Here is Fallback Handler";
    }

    @GetMapping("/resource2")
    @SentinelResource(value = "resource2",
            blockHandlerClass = MyBlockHandler.class, blockHandler = "myBlockHandler2",
            fallback = "fallbackHandler")
    public String resource2() {
//        int a = 0 / 0;
        return "resource2";
    }

}

