package com.raptor.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author raptor
 * @description ApplicationContextConfid
 * @date 2021/8/27 11:14
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
