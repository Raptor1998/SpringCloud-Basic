package com.raptor.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author raptor
 * @description MyBatisConfig
 * @date 2021/10/8 20:30
 */
@Configuration
@MapperScan({"com.raptor.dao"})
public class MyBatisConfig {

}