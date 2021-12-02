package com.raptor.dao;

import com.raptor.entity.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author raptor
 * @description PaymentDao
 * @date 2021/8/27 9:28
 */
@Mapper
public interface PaymentDao {

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
