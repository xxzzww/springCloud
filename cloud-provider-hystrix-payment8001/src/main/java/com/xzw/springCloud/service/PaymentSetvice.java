package com.xzw.springCloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class PaymentSetvice {
    //正常访问
    public String paymentInfo_OK(Integer id){
        return "线程池"+Thread.currentThread().getName()+"\tpaymentInfo_OK,id:"+id;
    }
    //模拟程序超时
    /*
    *@HystrixCommand 服务熔断
    * fallbackMethod服务出问题执行的方法,参数是方法名
    *   @HystrixProperty
    *       name什么问题
    *       value,值
    * 1.name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"
    * 设置了最大链接时间是3秒,但是我设置了睡眠5秒
    *
    */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
//        int a = 10/0;
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"\tpaymentInfo_TimeOut,id:"+id+"超时"+time+"秒!!!";
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"\tpaymentInfo_TimeOutHandler,id:"+id+",抱歉,连接超时或者程序运行错误";
    }

    //服务熔断
    //如果拍写错了可以打开 HystrixPropertiesManager 类,里面有常量
    @HystrixCommand(fallbackMethod = "PaymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少跳闸
    })
    public String PaymentCircuitBreaker(Integer id){

        if (id < 0) {
            throw new RuntimeException("id不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功,流水号:"+serialNumber;
    }

    private String  PaymentCircuitBreaker_fallback(Integer id){
        return "id不能是负数,id="+id;
    }

}
