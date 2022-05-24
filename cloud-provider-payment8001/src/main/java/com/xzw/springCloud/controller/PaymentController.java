package com.xzw.springCloud.controller;

import com.xzw.springCloud.entities.CommonResult;
import com.xzw.springCloud.entities.Payment;
import com.xzw.springCloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    //服务发现  获取服务信息
    @Resource
    private DiscoveryClient discoveryClient;
    //服务发现
    @GetMapping("/payment/dis")
    public Object discovery(){
        List<String> service = discoveryClient.getServices();
        for (String element :service){
            log.info("---getServices---:"+element);
        }
        //一个微服务下的全部实例   指是该服务器的名称
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance ser : instances){
            log.info("---getInstances---"+ser.getServiceId()+"\t"+ser.getHost()+"\t"+ser.getPort()+"\t"+ser.getUri());
        }
        return discoveryClient;
    }
//    插入操作建议用PostMapping注解
    @PostMapping("/payment/insert")
    public CommonResult insert(@RequestBody Payment payment){
        int result = paymentService.insert(payment);
        log.info("插入结果:"+(result>0?"成功":"失败"));
        if (result>0){
            return new CommonResult(222,"插入数据库成功",result);
        }
        return new CommonResult(444,"插入数据库失败",null);
    }
//    查询操作
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentByID(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentByID(id);
        log.info("根据id查询结果:"+payment);
        if (payment!=null){
            return new CommonResult(333,"查询成功",payment);
        }
        return new CommonResult(444,"查询失败,找不到该id:"+id,null);
    }
//    查询操作
    @GetMapping("/payment/getAll")
    public CommonResult getPaymentAll(){
        List<Payment> payment = paymentService.getPaymentAll();
        log.info("根据id查询结果:"+payment);
        if (payment!=null){
            return new CommonResult(300,"查询成功",payment);
        }
        return new CommonResult(444,"查询失败",null);
    }
}
