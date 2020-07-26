package com.allst.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author YiYa
 * @since 2020-07-26 下午 01:31
 */
@Component
@FeignClient(value = "cloud-hystrix-service")
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/info/{id}")
    public String getPaymentInfo(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/error/{id}")
    public String getPaymentError(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getPaymentTimeOut(@PathVariable("id") Integer id);
}
