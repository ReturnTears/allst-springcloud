package com.allst.springcloud.controller;

import com.allst.springcloud.entities.CommonResult;
import com.allst.springcloud.entities.Payment;
import com.allst.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YiYa
 * @since 2020-07-26 上午 09:09
 */
@RestController
public class FeignOrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") int id) {
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 模拟feign超时控制：客户端默认等待1s, 而服务端默认需要处理3s（故意为之）
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String payFeignTimeout() {
        return paymentFeignService.payFeignTimeout();
    }
}
