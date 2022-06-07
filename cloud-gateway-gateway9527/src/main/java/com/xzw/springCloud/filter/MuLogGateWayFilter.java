package com.xzw.springCloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Configuration
public class MuLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("*******过滤器*********"+new Date());
        //获取uname的参数
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        //如果是null
        if (uname == null) {
            System.out.println("非法用户");
            //返回状态码406
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //返回信息
            return exchange.getResponse().setComplete();
        }
        //放行
        return chain.filter(exchange);
    }
    //顺序
    @Override
    public int getOrder() {
        return 0;
    }
}
