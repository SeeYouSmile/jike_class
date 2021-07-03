package io.kimmking.rpcfx.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class RpcfxAOP {

    @Pointcut("execution(* io.kimmking.rpcfx.demo.provider.UserServiceImpl.*(..))")
    public void point(){

    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--------------------------------aop start-------------------------------");
        Object proceed = joinPoint.proceed();
        System.out.println("--------------------------------aop end-------------------------------");
    }
}
