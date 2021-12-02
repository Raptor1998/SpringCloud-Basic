package com.raptor.service;

import com.raptor.entity.domain.Payment;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author raptor
 * @description PaymentService
 * @date 2021/9/26 16:18
 */
@Component
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    public Result paymentSQL(@PathVariable("id") Long id);
}
