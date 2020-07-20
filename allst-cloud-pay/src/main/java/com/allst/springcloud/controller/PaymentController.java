package com.allst.springcloud.controller;

import com.allst.springcloud.entities.CommonResult;
import com.allst.springcloud.entities.Payment;
import com.allst.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YiYa
 * @since 2020-07-19 下午 11:56
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        System.out.println("-----------插入成功---------, 返回值: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功.", result);
        } else {
            return new CommonResult(444, "插入数据库失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            System.out.println(id + " : 查询成功");
            return new CommonResult(200, "查询成功.", payment);
        } else {
            return new CommonResult(444, "查询失败.无对应记录,id:" + id);
        }
    }
}
