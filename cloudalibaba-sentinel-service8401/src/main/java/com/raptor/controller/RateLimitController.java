package com.raptor.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.raptor.entity.domain.Payment;
import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raptor
 * @description RateLimitController
 * @date 2021/9/26 10:47
 */

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public Result byResource() {
        return ResultUtil.success("资源名称限流  byResource");
    }

    public Result handleException(BlockException exception) {
        return ResultUtil.fail(exception.getClass().getName() + "资源不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public Result byUrl() {
        return ResultUtil.success("按url限流测试OK");
    }

    //customerBlockHandler
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException")
    public Result byBlockHandler() {
        return ResultUtil.success("customerBlockHandler");
    }
}
