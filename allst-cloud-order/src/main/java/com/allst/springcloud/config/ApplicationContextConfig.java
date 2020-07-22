package com.allst.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置类
 * @author YiYa
 * @since 2020-07-20 下午 11:32
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced // 赋予RestTemplate负载均衡能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
