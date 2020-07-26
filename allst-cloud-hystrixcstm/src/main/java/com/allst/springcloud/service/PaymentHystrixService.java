package com.allst.springcloud.service;

import com.allst.springcloud.service.impl.PaymentHystrixFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 消费者服务接口
 * @author YiYa
 * @since 2020-07-26 下午 01:31
 */
@Component
@FeignClient(value = "cloud-hystrix-service", fallback = PaymentHystrixFallbackServiceImpl.class)   // fallback属性，表示消费者端服务service层面统一降级服务处理，这样做到了统一的降级服务处理，实现解耦
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/info/{id}")
    public String getPaymentInfo(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/error/{id}")
    public String getPaymentError(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getPaymentTimeOut(@PathVariable("id") Integer id);
}
