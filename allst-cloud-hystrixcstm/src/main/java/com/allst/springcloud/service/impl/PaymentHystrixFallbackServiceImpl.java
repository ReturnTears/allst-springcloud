package com.allst.springcloud.service.impl;

import com.allst.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * 消费者端服务service层面统一降级服务处理，新建类实现消费服务接口，统一为接口里的方法进行异常处理
 * @author YiYa
 * @since 2020-07-26 下午 11:11
 */
@Component
public class PaymentHystrixFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String getPaymentInfo(Integer id) {
        return "-----PaymentHystrixFallbackServiceImpl-----fallback----info----";
    }

    @Override
    public String getPaymentError(Integer id) {
        return "-----PaymentHystrixFallbackServiceImpl-----fallback----error----";
    }

    @Override
    public String getPaymentTimeOut(Integer id) {
        return "-----PaymentHystrixFallbackServiceImpl-----fallback----timeout----";
    }
}
