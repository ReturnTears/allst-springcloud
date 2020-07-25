package com.allst.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * lb包需要纳入到@ComponentScan扫描器扫描到的包及其子包下
 * 自定义LoadBalance算法接口：
 * 功能：获取服务总数
 * @author YiYa
 * @since 2020-07-25 下午 11:15
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
