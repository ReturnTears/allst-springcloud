package com.allst.springcloud.service;

/**
 * @author YiYa
 * @since 2020-07-26 下午 12:31
 */
public interface PaymentHystrixService {
    public String paymentInfo(Integer id);

    public String paymentError(Integer id);

    public String paymentInfoTimeOut(Integer id);
}
