package com.allst.springcloud.service.impl;

import com.allst.springcloud.service.PaymentHystrixService;
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
}
