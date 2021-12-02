package com.raptor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author raptor
 * @description TemplateConfig
 * @date 2021/9/26 17:15
 */
@Configuration
public class TemplateConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
