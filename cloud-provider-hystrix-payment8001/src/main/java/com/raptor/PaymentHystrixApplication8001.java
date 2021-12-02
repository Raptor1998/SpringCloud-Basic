package com.raptor;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author raptor
 * @description PaymentHystrixApplication8001
 * @date 2021/8/31 17:05
 */
@SpringBootApplication
@EnableEurekaClient
//注解开启断路器功能
@EnableCircuitBreaker
public class PaymentHystrixApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixApplication8001.class);
    }

    /**
     * 注意：新版本Hystrix需要在主启动类MainAppHystrix8001中指定监控路径
     *
     * @return
     */

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
