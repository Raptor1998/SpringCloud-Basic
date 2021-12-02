package com.raptor.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;

/**
 * @author raptor
 * @description CustomerBlockHandler
 * @date 2021/9/26 10:58
 */
public class CustomerBlockHandler {

    public static Result handlerException(BlockException exception) {
        return ResultUtil.success("自定义降级策略  1111");
    }

    public static Result handlerException2(BlockException exception) {
        return ResultUtil.success("自定义降级策略  2222");
    }
}
