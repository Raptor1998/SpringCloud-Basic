package com.raptor.service;

import com.raptor.entity.domain.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author raptor
 * @description PaymentService
 * @date 2021/8/27 9:36
 */
public interface PaymentService {
    /**
     * 创建订单
     *
     * @param payment
     * @return
     */
    Integer create(Payment payment);

    /**
     * 查询订单
     *
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}
