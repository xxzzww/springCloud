package com.xzw.springCloud.service;

import com.xzw.springCloud.entities.CommonResult;
import com.xzw.springCloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//要调用服务器的名称
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    //要调用的方法
    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentByID(@PathVariable("id") Long id);
    //超时测试
    @GetMapping(value = "/payment/timeout")
    String paymentFeignTimePut();
}
