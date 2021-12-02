package com.raptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author raptor
 * @description EurekaApplication
 * @date 2021/8/27 14:59
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication7001.class);
    }
}
