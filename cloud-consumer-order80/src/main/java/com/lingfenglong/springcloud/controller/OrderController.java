package com.lingfenglong.springcloud.controller;

import com.lingfenglong.springcloud.entity.CommonResult;
import com.lingfenglong.springcloud.entity.Payment;
import com.lingfenglong.springcloud.loadbanlance.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class OrderController {
   private final String PAYMENT_SERVICE_URL = "http://CLOUD-PAYMENT-SERVICE";
   private final RestTemplate restTemplate;
   private final DiscoveryClient discoveryClient;
   private final LoadBalancer loadBalancer;

    public OrderController(RestTemplate restTemplate, DiscoveryClient discoveryClient, LoadBalancer loadBalancer) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.loadBalancer = loadBalancer;
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_SERVICE_URL + "/payment/get/{id}", CommonResult.class, id);
    }

    @GetMapping("/payment/getForEntity/{id}")
    public CommonResult<?> getPaymentEntityById(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity =
                restTemplate.getForEntity(PAYMENT_SERVICE_URL + "/payment/get/{id}", CommonResult.class, id);
        return new CommonResult<>(entity.getStatusCode().value(), entity.toString());
    }

    @PostMapping("/payment/create")
    public CommonResult<?> createPayment(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/payment/create", payment, CommonResult.class);
    }

    @PostMapping("/payment/createForEntity")
    public CommonResult<?> createPaymentEntity(@RequestBody Payment payment) {
        ResponseEntity<CommonResult> entity =
                restTemplate.postForEntity(PAYMENT_SERVICE_URL + "/payment/create", payment, CommonResult.class);
        return new CommonResult<>(entity.getStatusCode().value(), entity.toString());
    }

    @GetMapping("/payment/lb")
    public String lb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        ServiceInstance serviceInstance = loadBalancer.serviceInstance(instances);
        URI uri = serviceInstance.getUri();
        System.out.println("uri = " + uri + "/payment/lb");

        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
