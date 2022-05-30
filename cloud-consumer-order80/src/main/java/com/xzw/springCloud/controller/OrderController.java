package com.xzw.springCloud.controller;

import com.xzw.springCloud.entities.CommonResult;
import com.xzw.springCloud.entities.Payment;
import com.xzw.springCloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    //单机版服务注册执行可以固定ip,但是集群版就要写服务名称了,让服务注册中心去负载均衡
//    public static final String payment_url="http://localhost:8001";
    public static final String payment_url="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;
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
        return discoveryClient.getInstances("cloud-payment-service1");
    }
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
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getForEntity(@PathVariable("id") Long id){
        log.info("用户查询所有");
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(payment_url+"/payment/get/"+id,CommonResult.class);
        //entity.getStatusCode().后面有is1xxSuccessful到is5xxSuccessful,还有error,除了2其他都是发生错误
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }
        return new CommonResult(444,"操作失败");
    }
    //测试自己写的轮询算法
    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances==null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        System.out.println(uri);
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
