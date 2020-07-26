package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * hystrix主启动类
 * @author YiYa
 * @since 2020-07-26 下午 12:30
 */
@SpringBootApplication
public class PaymentHystrixMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain.class, args);
    }
}
