package com.raptor.controller;

import com.raptor.entity.pojo.Result;
import com.raptor.entity.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raptor
 * @description TestController
 * @date 2021/9/26 17:12
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public Result hello() {
        return ResultUtil.success("hello 8888");
    }
}
