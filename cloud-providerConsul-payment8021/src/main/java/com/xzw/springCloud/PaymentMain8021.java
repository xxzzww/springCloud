package com.xzw.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8021 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8021.class,args);
    }
}
