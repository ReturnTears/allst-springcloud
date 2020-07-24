package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 主启动类
 * @author YiYa
 * @since 2020-07-24 下午 11:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsulMain.class, args);
    }
}
