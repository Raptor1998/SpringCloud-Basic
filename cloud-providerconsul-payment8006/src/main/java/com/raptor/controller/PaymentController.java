package com.raptor.controller;

import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author raptor
 * @description PaymentController
 * @date 2021/8/28 18:03
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/consul")
    public Result paymentZK() {
        return ResultUtil.defineSuccess("consul success", "spring cloud with consul: " + serverPort + "  " + UUID.randomUUID().toString());
    }

}
