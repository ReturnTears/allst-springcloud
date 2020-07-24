package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YiYa
 * @since 2020-07-24 下午 11:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulCustomMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsulCustomMain.class, args);
    }
}
