package com.xzw.springCloud.service;

import org.springframework.stereotype.Component;

@Component
//兜底类,如果PaymentHystrixService接口没有问题就正常使用,如果出现问题就用这个类兜底
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentHystrixServiceImpl----paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentHystrixServiceImpl----paymentInfo_TimeOut";
    }
}
