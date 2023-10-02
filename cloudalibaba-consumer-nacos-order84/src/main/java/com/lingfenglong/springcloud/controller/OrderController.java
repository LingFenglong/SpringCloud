package com.lingfenglong.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lingfenglong.springcloud.handler.blockhandler.MyBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    private final RestTemplate restTemplate;
    private final String URL = "http://cloud-provider-payment";

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get/{id}")
    @SentinelResource(value = "getById",
            fallback = "getByIdFallback",
            blockHandler = "getByIdBlockHandler")
    public String getById(@PathVariable("id") Long id) {
        String res = restTemplate.getForObject(URL + "/payment/get/" + id, String.class);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        } else if (id >= 5) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return res;
    }

    public String getByIdFallback(@PathVariable Long id, Throwable t) {
        return "产生了错误：" + Optional.of(t.getMessage()).orElse("") + "\r\n" +
                "id = " + id;
    }
    public static String getByIdBlockHandler(@PathVariable("id") Long id, BlockException e) {
        return "阻止访问";
//        return "阻止访问：" + Optional.of(e.getMessage()).orElse("") + "\r\n";
    }

}
