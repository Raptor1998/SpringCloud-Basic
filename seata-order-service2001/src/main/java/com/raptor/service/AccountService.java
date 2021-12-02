package com.raptor.service;

import com.raptor.entity.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author raptor
 * @description AccountService
 * @date 2021/10/8 20:27
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    Result decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
