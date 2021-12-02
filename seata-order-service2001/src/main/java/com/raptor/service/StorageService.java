package com.raptor.service;

import com.raptor.entity.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author raptor
 * @description StorageService
 * @date 2021/10/8 20:26
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {
    @PostMapping(value = "/storage/decrease")
    Result decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
