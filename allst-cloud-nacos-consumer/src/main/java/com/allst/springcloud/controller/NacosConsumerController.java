package com.allst.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YiYa
 * @since 2020-08-01 下午 10:23
 */
@RestController
public class NacosConsumerController {

    @Value("${server.port}")
    private String severPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayMent(@PathVariable("id") Integer id) {
        return "Nacos registry, serverPost : " + severPort + " \t id: " + id;
    }
}
