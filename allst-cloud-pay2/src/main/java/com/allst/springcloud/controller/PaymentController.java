package com.allst.springcloud.controller;

import com.allst.springcloud.entities.CommonResult;
import com.allst.springcloud.entities.Payment;
import com.allst.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author YiYa
 * @since 2020-07-19 下午 11:56
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    /**
     * 注意细节：其他服务调用本服务create方法时，需要添加注解@RequestBody,否则添加不上数据
     * @param payment 参数
     * @return 结构
     */
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        System.out.println("-----------插入成功---------, 返回值: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功.serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败.serverPort:" + serverPort);
        }
    }

    /**
     * 通过id查询数据
     * @param id 参数
     * @return 结构
     */
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            System.out.println(id + " : 查询成功");
            return new CommonResult(200, "查询成功.serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败.无对应记录,id:" + id + ", serverPort:" + serverPort);
        }
    }
}
