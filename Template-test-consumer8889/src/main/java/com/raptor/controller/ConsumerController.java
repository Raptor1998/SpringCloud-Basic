package com.raptor.controller;

import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author raptor
 * @description ConsumerController
 * @date 2021/9/26 17:16
 */
@RestController
public class ConsumerController {

    private RestTemplate restTemplate;

    @Autowired
    public ConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer/hello")
    public Result hello() {
        return restTemplate.getForObject("http://127.0.0.1:8888/hello", Result.class);
    }
}
