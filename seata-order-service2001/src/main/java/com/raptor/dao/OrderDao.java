package com.raptor.dao;

import com.raptor.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author raptor
 * @description OrderDao
 * @date 2021/10/8 20:18
 */
@Mapper
public interface OrderDao {
    //新建订单
    void create(Order order);

    //修改订单状态，从零改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
