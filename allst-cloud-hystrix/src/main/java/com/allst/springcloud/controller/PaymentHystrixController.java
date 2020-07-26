package com.allst.springcloud.controller;

import com.allst.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YiYa
 * @since 2020-07-26 下午 12:38
 */
@RestController
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/info/{id}")
    public String getPaymentInfo(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo(id) + " - " + serverPort;
    }

    @GetMapping(value = "/payment/hystrix/error/{id}")
    public String getPaymentError(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentError(id) + " - " + serverPort;
    }
}
