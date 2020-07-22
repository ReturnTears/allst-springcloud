package com.allst.springcloud.service;

import com.allst.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author YiYa
 * @since 2020-07-19 下午 11:52
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") int id);
}
