package com.raptor.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.raptor.entity.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author raptor
 * @description PaymentService
 * @date 2021/8/31 17:32
 */
@Component
@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public Result paymentInfo(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public Result paymentInfoTimeout(@PathVariable("id") Integer id);

}
