package com.xzw.springCloud.controller;

import com.xzw.springCloud.entities.CommonResult;
import com.xzw.springCloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
@RestController
@Slf4j
public class OrderController {
    public static final String payment_url="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(payment_url+"/payment/consul",String.class);
        return "用户端:" + serverPort + "访问:\t" +result;
    }
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/consul")
    public String paymentConsul() {
        return "用户端访问 consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
