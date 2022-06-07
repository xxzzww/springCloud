package com.xzw.springCloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.con}")
    private String configCon;
    @Value("${config.info}")
    private String configInfo;
    @Value("${config.name}")
    private String configName;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "端口:"+serverPort+"\t,Info配置:"+configInfo;
    }
    @GetMapping("/configName")
    public String getConfigName(){
        return "端口:"+serverPort+"\t,name配置:"+configName;
    }
    @GetMapping("/configCon")
    public String getConfigCon(){
        return "端口:"+serverPort+"\t,Con配置:"+configCon;
    }
    @GetMapping("/All")
    public String getAll(){
        return "端口:"+serverPort+"\t,Con配置:"+configCon+"\t,name配置:"+configName+"\t,Info配置:"+configInfo;
    }
}
