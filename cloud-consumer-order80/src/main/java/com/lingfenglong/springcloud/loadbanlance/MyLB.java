package com.lingfenglong.springcloud.loadbanlance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private int getAndIncrement() {
        while (true) {
            int current = atomicInteger.get();
            int next;

            if (current == Integer.MAX_VALUE) {
                next = 0;
            } else {
                next = current + 1;
            }

            if (atomicInteger.compareAndSet(current, next)) {
                System.out.println("next = " + next);
                return current;
            }
        }
    }

    @Override
    public ServiceInstance serviceInstance(List<ServiceInstance> instance) {
        System.out.println("instance = " + instance);
        int index = getAndIncrement();
        if (instance == null || instance.size() == 0) {
            return null;
        }
        return instance.get(index % instance.size());
    }
}
