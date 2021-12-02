package com.raptor.service;


import com.raptor.entity.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author raptor
 * @description PaymentFeignService
 * @date 2021/8/30 20:57
 */
@Component
@FeignClient(name = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    Result getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/lb/timeout")
    public String getPaymentLBTimeout();
}
