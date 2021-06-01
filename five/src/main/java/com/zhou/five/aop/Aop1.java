package com.zhou.five.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class Aop1 {

    //前置通知
    public void start(){
        System.out.println("=====start ding...");//2
    }

    //后置通知
    public void end(){
        System.out.println("=====end ding...");//3
    }

    //环绕通知
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=====around begin ding");//1
        joinPoint.proceed();//执行实际被代理的方法
        System.out.println("=====around finish ding");//4
    }
}
