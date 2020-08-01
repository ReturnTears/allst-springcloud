package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YiYa
 * @since 2020-08-01 下午 10:22
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain.class, args);
    }
}
