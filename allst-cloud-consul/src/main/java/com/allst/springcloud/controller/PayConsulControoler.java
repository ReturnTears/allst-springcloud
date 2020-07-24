package com.allst.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author YiYa
 * @since 2020-07-24 下午 11:05
 */
@RestController
public class PayConsulControoler {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "payment/consul")
    public String paymentZookeeper() {
        return "SpringCloud With Zookeeper : " + serverPort + " - " + UUID.randomUUID().toString();
    }
}
