package com.raptor.lb;

import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author raptor
 * @description MyLoadBalance
 * @date 2021/8/30 20:25
 */
@Component
@Slf4j
public class MyLoadBalance implements LoadBalance {
    //自定义负载均衡算法
    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = this.incrementAndGetModulo() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    private int incrementAndGetModulo() {
        int current;
        int next;
        do {
            current = this.nextServerCyclicCounter.get();
            next = current > Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.nextServerCyclicCounter.compareAndSet(current, next));
        log.info("次数next:{}", next);
        return next;
    }
}
