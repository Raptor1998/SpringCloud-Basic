package com.raptor.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raptor
 * @description PaymentController
 * @date 2021/8/31 17:32
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")  //全局的
public class OrderHystrixController {

    private PaymentHystrixService paymentService;

    @Autowired
    public OrderHystrixController(@Qualifier("com.raptor.service.PaymentHystrixService") PaymentHystrixService paymentService) {
        this.paymentService = paymentService;
    }

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public Result paymentInfo(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo(id);
    }

    //@GetMapping("/payment/hystrix/timeout/{id}")
    //public Result paymentInfoTimeout(@PathVariable("id") Integer id) {
    //    return paymentService.paymentInfoTimeout(id);
    //}

    @GetMapping("/payment/hystrix/timeout/{id}")
    //@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
    //})
    @HystrixCommand
    public Result paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        //int a = 10 / 0;
        Result result = paymentService.paymentInfoTimeout(id);
        return result;
    }

    //兜底方法
    public Result paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return ResultUtil.success("我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)");
    }

    public Result payment_Global_FallbackMethod() {
        return ResultUtil.success("Global异常处理信息，请稍后再试,(┬＿┬)");
    }

}

