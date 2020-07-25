package com.allst.springcloud.controller;

import com.allst.springcloud.entities.CommonResult;
import com.allst.springcloud.entities.Payment;
import com.allst.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author YiYa
 * @since 2020-07-20 下午 11:27
 */
@RestController
@Slf4j
public class OrderController {

    // public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-pay-service";

    @Resource
    private RestTemplate restTemplate;
    /**
     * 使用自定义的LB
     */
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * create
     * @param payment 参数
     * @return 结果
     */
    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    /**
     * 获取数据
     * @param id 参数
     * @return 结果
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/entity/{id}")
    public CommonResult getPaymentWithRest(@PathVariable("id") Integer id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult(444, "操作失败");
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-pay-service");
        if (instances == null || instances.size() <= 0)
            return null;
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
