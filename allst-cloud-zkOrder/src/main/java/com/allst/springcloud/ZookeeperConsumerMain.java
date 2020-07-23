package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YiYa
 * @since 2020-07-23 下午 11:57
 */
@SpringBootApplication
@EnableDiscoveryClient  // 该注解用于向使用consul或者zookeeper作为注册中心时注册服务。
public class ZookeeperConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperConsumerMain.class, args);
    }
}
