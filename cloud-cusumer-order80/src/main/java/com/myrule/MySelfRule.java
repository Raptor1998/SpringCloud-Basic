package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author raptor
 * @description MySelfRule
 * @date 2021/8/30 19:56
 */
//@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        //定义为随机
        return new RandomRule();
    }
}
