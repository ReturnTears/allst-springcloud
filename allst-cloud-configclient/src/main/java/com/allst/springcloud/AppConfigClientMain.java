package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author YiYa
 * @since 2020-07-30 上午 12:20
 */
@SpringBootApplication
@EnableEurekaClient
public class AppConfigClientMain {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigClientMain.class, args);
    }
}

