package com.lingfenglong.springcloud.blockhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

import java.util.Optional;

public class MyBlockHandler {
    public static String myBlockHandler2(BlockException e) {
        return "Here is Block Handler 2\r\n" + Optional.ofNullable(e.getMessage());
    }
}
