package com.xzw.springCloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    /*
    * 配置了一个id为customRouteLocator_baidu的路由规则:
    *   当访问是http://localhost:9527/index.html时,会自动跳转到https://tieba.baidu.com/index.html里,
    * 注意两个路径的后面(index.html)要是一样的,不一样会报404错误
    * */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder  routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes=routeLocatorBuilder.routes();
        routes.route("customRouteLocator_baidu", //id,必须唯一
                r ->r.path("/index.html")   //输入的url
                        .uri("https://tieba.baidu.com/index.html"))   //映射的url,意思就是你输入guonei,会访问到https://www.baidu.com/里
                            .build();
        return routes.build();
    }
}
