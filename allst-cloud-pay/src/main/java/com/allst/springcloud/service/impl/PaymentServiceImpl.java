package com.allst.springcloud.service.impl;

import com.allst.springcloud.dao.PaymentDao;
import com.allst.springcloud.entities.Payment;
import com.allst.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YiYa
 * @since 2020-07-19 下午 11:52
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired // 或者使用Java自带的的注解：@Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentDao.getPaymentById(id);
    }
}
