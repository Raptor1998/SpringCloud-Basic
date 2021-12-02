package com.raptor.controller;

import com.raptor.entity.domain.Payment;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author raptor
 * @description PaymentController
 * @date 2021/9/26 11:30
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L, new Payment(2L, "bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L, new Payment(3L, "6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public Result paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return ResultUtil.define("200", "from mysql,serverPort:  " + serverPort, payment);
    }


}