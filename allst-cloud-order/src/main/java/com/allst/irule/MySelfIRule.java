package com.allst.irule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon配置类，以满足特性的需求
 * @author YiYa
 * @since 2020-07-25 下午 10:18
 */
@Configuration
public class MySelfIRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();    // 自定义随机算法
    }
}
