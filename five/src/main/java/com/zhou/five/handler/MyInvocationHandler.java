package com.zhou.five.handler;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Data
public class MyInvocationHandler implements InvocationHandler {

    private Object bean;

    public MyInvocationHandler(Object bean){
        this.bean=bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=====proxy start=====");
        System.out.println("调用类名称："+bean.getClass().getName());
        System.out.println("调用方法名称："+method.getName());
        Object invoke = method.invoke(bean, args);//调用原方法
        System.out.println("=====proxy end=====");
        return invoke;
    }
}
