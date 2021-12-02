package com.raptor.controller;

import com.raptor.domain.Order;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import com.raptor.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author raptor
 * @description OrderController
 * @date 2021/10/8 20:29
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public Result create(Order order) {
        orderService.create(order);
        return ResultUtil.success(order);
    }
}
