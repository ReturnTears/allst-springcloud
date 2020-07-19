package com.allst.springcloud.dao;

import com.allst.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author YiYa
 * @since 2020-07-19 下午 11:36
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") int id);

}
