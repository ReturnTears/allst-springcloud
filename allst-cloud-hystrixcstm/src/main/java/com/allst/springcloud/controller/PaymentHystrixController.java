package com.allst.springcloud.controller;

import com.allst.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YiYa
 * @since 2020-07-26 下午 01:35
 */
@RestController
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/info/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        return paymentHystrixService.getPaymentInfo(id);
    }

    @GetMapping("/consumer/payment/hystrix/error/{id}")
    public String paymentError(@PathVariable("id") Integer id) {
        return paymentHystrixService.getPaymentError(id);
    }
}
