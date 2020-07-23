package com.allst.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author YiYa
 * @since 2020-07-23 下午 10:14
 */
@RestController
public class ZookeeperController {

    @Value("${server.port}")
    private String servePost;

    @GetMapping(value = "payment/zk")
    public String paymentZookeeper() {
        return "SpringCloud With Zookeeper : " + servePost + " - " + UUID.randomUUID().toString();
    }

}
