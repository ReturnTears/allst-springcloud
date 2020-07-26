package com.allst.springcloud.service;

import com.allst.springcloud.entities.CommonResult;
import com.allst.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign接口注解
 * @author YiYa
 * @since 2020-07-26 上午 08:54
 */
@Component
@FeignClient(value = "cloud-pay-service")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") int id);
}
