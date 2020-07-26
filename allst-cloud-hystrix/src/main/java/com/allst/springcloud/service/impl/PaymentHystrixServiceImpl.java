package com.allst.springcloud.service.impl;

import com.allst.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
     * @param id 参数
     * @return 结果
     */
    public String paymentInfoTimeOutHandlers(Integer id) {
        return "method : paymentInfoTimeOutHandlers | 系统繁忙，请稍后再试!";
    }
}
