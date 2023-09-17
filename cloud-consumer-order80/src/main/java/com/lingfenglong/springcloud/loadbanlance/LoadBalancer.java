package com.lingfenglong.springcloud.loadbanlance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    ServiceInstance serviceInstance(List<ServiceInstance> instance);
}
