package com.raptor.controller;

import com.raptor.entity.domain.Payment;
import com.raptor.entity.enumerate.ResultEnum;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author raptor
 * @description PaymentController
 * @date 2021/8/27 9:38
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    private DiscoveryClient discoveryClient;

    @Autowired
    public PaymentController(PaymentService paymentService, DiscoveryClient discoveryClient) {
        this.paymentService = paymentService;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping("/create")
    public Result create(@RequestBody Payment payment) {
        log.info("服务提供者:{}", serverPort);
        Integer flag = paymentService.create(payment);
        log.info("插入结果，{},payment：{}", flag, payment);
        if (flag > 0) {
            return ResultUtil.success(payment);
        } else {
            return ResultUtil.defineFail(ResultEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public Result getPayment(@PathVariable Long id) {
        log.info("服务提供者:{}", serverPort);
        Payment payment = paymentService.getPaymentById(id);
        if (!ObjectUtils.isEmpty(payment)) {
            return ResultUtil.success(payment);
        } else {
            return ResultUtil.defineFail(ResultEnum.PAYMENT_NOT_EXIST);
        }
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("element:{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/lb/timeout")
    public String getPaymentLBTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }


}
