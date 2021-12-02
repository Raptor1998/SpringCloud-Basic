package com.raptor;

import com.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author raptor
 * @description OrderApplication
 * @date 2021/8/27 11:10
 */
@SpringBootApplication
@EnableEurekaClient
// ribbon的负载均衡配置
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication80.class);
    }
}


// ribbon的负载均衡配置原理
/**
 *
 */