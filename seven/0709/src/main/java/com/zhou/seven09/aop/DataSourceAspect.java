package com.zhou.seven09.aop;

import com.zhou.seven09.annotation.CurDataSource;
import com.zhou.seven09.datasource.DataSourceNames;
import com.zhou.seven09.datasource.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    //对带有CurDataSource注解的方法进行aop增强
    @Pointcut("@annotation(com.zhou.seven09.annotation.CurDataSource)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point){
        MethodSignature signature= (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        CurDataSource annotation = method.getAnnotation(CurDataSource.class);
        //如果可以获取到CurDataSource注解（这里应该不会获取不到），设置数据库为注解的value值
        if(annotation!=null){
            DynamicDataSource.setDataSource(annotation.value());
        }else{
            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
        }
        try {
            //执行原方法
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }finally {
            DynamicDataSource.clearDataSource();
        }
    }

}
