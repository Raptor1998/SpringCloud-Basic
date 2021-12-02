package com.raptor.service;

import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import org.springframework.stereotype.Component;

/**
 * @author raptor
 * @description PaymentFallbackService
 * @date 2021/9/1 9:37
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public Result paymentInfo(Integer id) {
        return ResultUtil.success("-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)");
    }

    @Override
    public Result paymentInfoTimeout(Integer id) {
        return ResultUtil.success("-----PaymentFallbackService fail back-paymentInfo_TimeOut  , (┬＿┬)");
    }
}
