package com.xzw.springCloud.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
//    插入操作建议用PostMapping注解
    @PostMapping("/payment/insert")
    public String insert(){
        return "s";
    }
}
