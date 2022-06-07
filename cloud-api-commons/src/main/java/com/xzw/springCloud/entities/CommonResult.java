package com.xzw.springCloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T>{
    private static final long serialVersionUID = -1245787855635487949L;
    //异常编号
    private Integer code;
    //异常信息
    private String message;
    //泛型,返回类型
    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}


