package com.allst.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author YiYa
 * @since 2020-07-24 下午 11:17
 */
@RestController
public class ConsulCustomController {

    public static final String PAYMENT_URL = "http://cloud-consul-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String create() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
    }
}
