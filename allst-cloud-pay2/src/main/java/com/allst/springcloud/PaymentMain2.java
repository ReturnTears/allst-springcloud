package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author YiYa
 * @since 2020-07-22 下午 10:51
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain2 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain2.class, args);
    }
}
