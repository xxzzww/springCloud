package com.xzw.springCloud.controller;

import com.xzw.springCloud.entities.CommonResult;
import com.xzw.springCloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    public static final String payment_url="http://localhost:8001";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/payment/insert")
    public CommonResult insert(Payment payment){
        log.info("用户添加了:"+payment);
        return restTemplate.postForObject(payment_url+"/payment/insert",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentByID(@PathVariable("id") Long id){
        log.info("用户根据id查询:id="+id);
        return restTemplate.getForObject(payment_url+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/getAll")
    public CommonResult getPaymentAll(Payment payment){
        log.info("用户查询所有");
        return restTemplate.getForObject(payment_url+"/payment/getAll",CommonResult.class);
    }
}
