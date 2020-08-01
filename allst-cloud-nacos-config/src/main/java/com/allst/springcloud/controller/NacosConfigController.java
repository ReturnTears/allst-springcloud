package com.allst.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YiYa
 * @since 2020-08-01 下午 11:42
 */
@RestController
@RefreshScope   // 支持Nacos动态刷新功能
public class NacosConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/info")
    public String getConfigInfo() {
        return  configInfo;
    }
}
