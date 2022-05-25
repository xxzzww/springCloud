package com.xzw.springCloud.service;

import com.xzw.springCloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentService {

    int insert(Payment payment);//添加
    Payment getPaymentByID(@Param("id") Long id);//根据id查询
    List<Payment> getPaymentAll();//查询所有
}
