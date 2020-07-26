package com.allst.springcloud.controller;

import com.allst.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    /**
     * 消费者端服务降级处理:一旦消费者端服务不可用，就会做服务降级，找到兜底的解决方案fallbackMethod
     * 注意：
     *      我们自己配置过的热部署对Java代码改动重启有效，但对@HystrixCommand内属性的修改建议重启服务。
     * @param id 参数
     * @return 结果
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentTimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.getPaymentTimeOut(id);
    }

    /**
     * 服务降级调用的方法
     * @param id 参数
     * @return 结果
     */
    public String paymentTimeOutHandler(@PathVariable("id") Integer id) {
        return "消费者端调用服务降级方法: paymentTimeOutHandler - " + id;
    }
}
