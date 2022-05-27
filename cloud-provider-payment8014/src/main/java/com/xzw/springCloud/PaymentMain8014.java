package com.xzw.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//该注解用于向使用consul或者zookeeper柞为注册中心时注册服务
public class PaymentMain8014 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8014.class,args);
    }
}
