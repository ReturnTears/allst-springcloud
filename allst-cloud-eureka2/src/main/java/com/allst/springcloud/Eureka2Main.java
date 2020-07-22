package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author YiYa
 * @since 2020-07-22 下午 10:24
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka2Main {
    public static void main(String[] args) {
        SpringApplication.run(Eureka2Main.class, args);
    }
}
