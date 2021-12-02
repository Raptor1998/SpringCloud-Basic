package com.raptor.service.impl;

import com.raptor.entity.domain.Payment;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.service.PaymentService;
import org.springframework.stereotype.Component;

/**
 * @author raptor
 * @description PaymentFallbackService
 * @date 2021/9/26 16:19
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public Result paymentSQL(Long id) {
        Payment payment = new Payment(id, "null");
        return ResultUtil.define("400", "服务降级策略，----PaymentFallbackService", payment);
    }
}
