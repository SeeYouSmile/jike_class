package com.zhou.five.aop;

import com.zhou.five.annotation.MyCache;
import com.zhou.five.cache.MethodCache;
import com.zhou.five.pojo.ObjectCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

@Aspect
public class Aop2 {

    @Autowired
    private MethodCache methodCache;//自定义缓存类

    @Pointcut(value = "execution(* com.zhou.five.*.Klass.dong(..))")
    public void point(){

    }

    @Before(value = "point()")
    public void before(){
        System.out.println("=====before klass dong...");//2
    }

    @AfterReturning(value = "point()")
    public void after(){
        System.out.println("=====after klass dong...");//3
    }

    @Around(value = "point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=====around begin klass dong");//1
        Object proceed = joinPoint.proceed();
        System.out.println("=====around around klass dong");//4
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        //获取调用的方法
        Method method = Class.forName(className).getMethod(methodName, null);
        //是否存在MyCache注解
        MyCache myCache = method.getAnnotation(MyCache.class);
        if(myCache!=null){
            int time = myCache.value();
            //添加缓存
            methodCache.set(methodName,new ObjectCache(proceed,time,System.currentTimeMillis()));
        }
    }
}
