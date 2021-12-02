package com.raptor.controller;

import com.raptor.entity.domain.Payment;
import com.raptor.entity.enumerate.ResultEnum;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.lb.LoadBalance;
import com.raptor.lb.MyLoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author raptor
 * @date 2021/8/27 11:11
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {
    private RestTemplate restTemplate;

    //private static final String PAYMENT_URL = "http://localhost:8001/payment";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    private MyLoadBalance myLoadBalance;
    private DiscoveryClient discoveryClient;

    @Autowired
    public OrderController(RestTemplate restTemplate, MyLoadBalance myLoadBalance, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.myLoadBalance = myLoadBalance;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping("/create")
    public Result create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, Result.class);
    }

    @GetMapping("/get/{id}")
    public Result getPayment(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, Result.class);
    }


    @GetMapping("/getForEntity/{id}")
    public Result getPayment2(@PathVariable Long id) {
        ResponseEntity<Result> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, Result.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode() + "\t" + entity.getHeaders());
            return entity.getBody();
        } else {
            return ResultUtil.defineFail(ResultEnum.FAIL);
        }
    }

    @GetMapping("/get/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() < 0) {
            return null;
        } else {
            ServiceInstance serviceInstance = myLoadBalance.instance(instances);
            URI uri = serviceInstance.getUri();
            return restTemplate.getForObject(uri + "/payment/lb", String.class);
        }
    }

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        String object = restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
        return object;
    }
}
