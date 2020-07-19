package com.allst.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YiYa
 * @since 2020-07-19 下午 08:55
 */
@RestController
public class PayController {
    @RequestMapping("/pay")
    public String payDemo() {
        System.out.println("pay");
        return "Pay";
    }
}
