package com.allst.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.allst.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author YiYa
 * @since 2020-07-26 下午 12:32
 */
@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    /**
     * 正常调用的方法
     * @param id 参数
     * @return 结果
     */
    @Override
    public String paymentInfo(Integer id) {
        System.out.println("线程name: " + Thread.currentThread().getName());
        return "线程池: " + Thread.currentThread().getName() + " - paymentInfo - id : " + id;
    }

    /**
     * 异常调用的方法
     * @return 结果
     */
    @Override
    public String paymentError(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程name: " + Thread.currentThread().getName());
        return "线程池: " + Thread.currentThread().getName() + " - paymentError - id : " + id;
    }

    /**
     * 服务降级案例:一旦调用服务方法失败，并抛出错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod属性中指定的方法
     * 注意: 一旦发现调用服务不可用(包括：异常和错误)，就会做服务降级，找到兜底的解决方案fallbackMethod
     *      我们自己配置过的热部署对Java代码改动重启有效，但对@HystrixCommand内属性的修改建议重启服务。
     * @param id 参数
     * @return 结果
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandlers", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentInfoTimeOut(Integer id) {
        try {
            //int result = id / 0; // 故意为之的错误
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "method : paymentInfoTimeOut";
    }

    /**
     * 服务降级调用的方法
     * 当前服务降级的弊端：
     *      1、每个业务方法都需要一个降级方法， 导致代码膨胀
     *      2、业务方法和降级方法在同一个class下， 导致代码高度耦合
     * @param id 参数
     * @return 结果
     */
    public String paymentInfoTimeOutHandlers(Integer id) {
        return "method : paymentInfoTimeOutHandlers | 系统繁忙，请稍后再试!";
    }

    /**
     * 服务熔断
     * HystrixProperty注解依次解释：
     * 开启断路器
     * 请求次数
     * 时间窗口期
     * 失败率达到多少后跳闸
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreakers(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("---------id can not be minus-------");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t 调用成功，流水号:" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "id isn`t can be minus, please try again a later.-- id : " + id;
    }
}
