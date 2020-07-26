package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author YiYa
 * @since 2020-07-26 下午 01:28
 */
@SpringBootApplication
@EnableFeignClients
public class OrderHystrixMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain.class, args);
    }
}
