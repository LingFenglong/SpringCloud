package com.lingfenglong.springcloud.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private Integer serverPort;
    public static HashMap<Long,Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L,new Payment(1L,"28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L,new Payment(2L,"bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L,new Payment(3L,"6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping("/test")
    public String test() {
        return "This is payment " + serverPort + " test";
    }

    @GetMapping(value = "/get/{id}")
    public String paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return "serverPort = " + serverPort + "\r\n" +
                "payment = " + payment;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Payment {
    private Long id;
    private String name;
}