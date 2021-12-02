package com.raptor.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author raptor
 * @description LoadBalance
 * @date 2021/8/30 20:22
 */
public interface LoadBalance {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
