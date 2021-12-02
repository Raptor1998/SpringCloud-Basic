package com.raptor.controller;

import com.raptor.entity.domain.Payment;
import com.raptor.entity.enumerate.ResultEnum;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
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

    @GetMapping(value = "/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
