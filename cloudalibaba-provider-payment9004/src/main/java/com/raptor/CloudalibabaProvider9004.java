package com.raptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author raptor
 * @description CloudalibabaProvider9003
 * @date 2021/9/26 11:27
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudalibabaProvider9004 {
    public static void main(String[] args) {
        SpringApplication.run(CloudalibabaProvider9004.class);
    }
}
