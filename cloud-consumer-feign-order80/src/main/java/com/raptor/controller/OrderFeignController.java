package com.raptor.controller;

import com.raptor.entity.pojo.Result;
import com.raptor.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raptor
 * @description OrderFeignController
 * @date 2021/8/30 21:00
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderFeignController {
    private PaymentFeignService paymentFeignService;

    @Autowired
    public OrderFeignController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @GetMapping("/payment/get/{id}")
    public Result getPayment(@PathVariable Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/lb/timeout")
    public String getPaymentLBTimeout() {
        //客户端默认等待一秒钟
        return paymentFeignService.getPaymentLBTimeout();
    }

}
