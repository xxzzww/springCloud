package com.xzw.springCloud.service.impl;

import com.xzw.springCloud.Mapper.PaymentMapper;
import com.xzw.springCloud.entities.Payment;
import com.xzw.springCloud.service.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Transactional//自己加的
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;
    @Override
    public int insert(Payment payment) {
        return paymentMapper.insert(payment);
    }

    @Override
    public Payment getPaymentByID(Long id) {
        return paymentMapper.getPaymentByID(id);
    }

    @Override
    public List<Payment> getPaymentAll() {
        return paymentMapper.getPaymentAll();
    }
}
