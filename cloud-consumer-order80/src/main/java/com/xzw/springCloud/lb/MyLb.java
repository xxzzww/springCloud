package com.xzw.springCloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalancer {
    //定义原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public final int getAtomicInteger(){
        int current;
        int next;
        do {
            //等于当前的原子数
            current = atomicInteger.get();
            //如果大于int的最大值就归零如果不是就等于当前原子数加一
            next = current>=2147483647?0:current+1;
        }while (!atomicInteger.compareAndSet(current,next));//current期望值,next更新后的值
        System.out.println("***************第几次次数:next="+next);
        return next;
    }
    //参数是当前服务器有多少接口
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstance) {
        //返回    访问次数%服务器总数,保证一直轮询
        int index = getAtomicInteger() % serviceInstance.size();
        //返回这个下标的服务器地址
        return serviceInstance.get(index);
    }
}
