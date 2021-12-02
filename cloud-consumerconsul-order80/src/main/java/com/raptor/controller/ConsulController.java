package com.raptor.controller;

import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author raptor
 * @description ZKController
 * @date 2021/8/28 19:00
 */
@RestController
@Slf4j
public class ConsulController {

    public static final String INVOKE_URL = "http://consul-provider-payment";

    private RestTemplate restTemplate;

    @Autowired
    public ConsulController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer/payment/consul")
    public Result paymentInfo() {
        Result object = restTemplate.getForObject(INVOKE_URL + "/payment/consul", Result.class);
        return ResultUtil.success(object);
    }
}
