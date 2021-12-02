package com.raptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author raptor
 * @description PaymentConsulApplication8006
 * @date 2021/8/30 18:01
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsulApplication8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulApplication8006.class);
    }
}
