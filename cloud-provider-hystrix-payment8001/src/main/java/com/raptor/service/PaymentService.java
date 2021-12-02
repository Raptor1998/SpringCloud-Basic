package com.raptor.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author raptor
 * @description PaymentService
 * @date 2021/8/31 17:06
 */
@Service
@Slf4j
public class PaymentService {
    /**
     * 正常方法
     *
     * @param id
     * @return
     */
    public String paymentInfo(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_OK，id: " + id + "\t" + "O(∩_∩)O小心心哈哈~";
    }


    /**
     * 当服务不可用时，进行服务降级
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
            //自定义 3 秒钟 以内  超时 则运行兜底方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentTimeout(Integer id) {
        int timeNumber = 1000;
        //int a = 10 / 0;
        try {
            Thread.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("paymentTimeout");
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_OK，id: " + id + "\t" + "O(∩_∩)O哈哈~" + " 耗时" + "秒";
    }

    /**
     * 兜底处理函数
     *
     * @param id
     * @return
     */
    public String paymentTimeoutHandler(Integer id) {
        log.info("paymentTimeoutHandler");
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfoHandler_OK，id: " + id + "\t" + "(*^▽^*)";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }

}
