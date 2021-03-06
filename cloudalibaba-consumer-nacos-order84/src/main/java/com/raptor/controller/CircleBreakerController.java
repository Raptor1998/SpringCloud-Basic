package com.raptor.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.raptor.entity.domain.Payment;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author raptor
 * @description CircleBreakerController
 * @date 2021/9/26 15:35
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    private PaymentService paymentService;
    private RestTemplate restTemplate;

    @Autowired
    public CircleBreakerController(PaymentService paymentService, RestTemplate restTemplate) {
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") //没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public Result fallback(@PathVariable Long id) {
        Result result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, Result.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    //fallback
    public Result handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return ResultUtil.define("400", "兜底异常handlerFallback,exception内容  " + e.getMessage(), payment);
    }

    //blockHandler
    public Result blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return ResultUtil.define("400", "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(), payment);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public Result getPayment(@PathVariable Long id) {
        return paymentService.paymentSQL(id);
    }
}


