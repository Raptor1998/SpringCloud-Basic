package com.raptor.controller;

import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raptor
 * @description PaymentController
 * @date 2021/8/31 17:09
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/hystrix/ok/{id}")
    public Result paymentInfo(@PathVariable Integer id) {
        String s = paymentService.paymentInfo(id);
        log.info("result: {}", s);
        return ResultUtil.success(s);
    }

    @GetMapping("/hystrix/timeout/{id}")
    public Result paymentInfoTimeout(@PathVariable Integer id) {
        String s = paymentService.paymentTimeout(id);
        log.info("result: {}", s);
        return ResultUtil.success(s);
    }

    //===服务熔断
    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*******result:"+result);
        return result;
    }

}
