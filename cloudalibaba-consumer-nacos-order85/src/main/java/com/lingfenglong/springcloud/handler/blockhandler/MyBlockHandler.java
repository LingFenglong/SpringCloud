package com.lingfenglong.springcloud.handler.blockhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.PathVariable;

public class MyBlockHandler {
    public static String getByIdBlockHandler(@PathVariable("id") Long id, BlockException e) {
        return "阻止访问";
    }
}
