package com.raptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author raptor
 * @date 2021/8/27 9:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplicationZK80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationZK80.class);
    }
}
