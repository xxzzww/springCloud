package com.xzw.springCloud.controller;

import com.xzw.springCloud.entities.CommonResult;
import com.xzw.springCloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentByID(@PathVariable("id") Long id){
        log.info("用户根据id查询:id="+id);
        return paymentFeignService.getPaymentByID(id);
    }
    @GetMapping(value = "/consumer/payment/timeout")
    public String paymentFeignTimePut(){
        //客户端默认等待1秒钟,但是这个程序会停3秒
        return paymentFeignService.paymentFeignTimePut();
    };
}
