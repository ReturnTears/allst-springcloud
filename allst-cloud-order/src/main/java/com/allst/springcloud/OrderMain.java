package com.allst.springcloud;

import com.allst.irule.MySelfIRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 启动类
 * @author YiYa
 * @since 2020-07-20 下午 11:24
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "cloud-pay-service", configuration = MySelfIRule.class)// 使用自定义的配置类修改默认的轮询算法
public class OrderMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class, args);
    }
}
