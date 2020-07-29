package com.allst.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 应用配置中心启动类
 * @author YiYa
 * @since 2020-07-29 下午 11:44
 */
@SpringBootApplication
@EnableConfigServer
public class AppConfigCenterMain {

    public static void main(String[] args) {
        SpringApplication.run(AppConfigCenterMain.class, args);
    }

}
