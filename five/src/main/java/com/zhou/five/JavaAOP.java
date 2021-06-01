package com.zhou.five;

import com.zhou.five.aop.ITeacher;
import com.zhou.five.handler.MyInvocationHandler;
import com.zhou.five.pojo.Teacher;

import java.lang.reflect.Proxy;

public class JavaAOP {
    public static void main(String[] args) {
        ITeacher realTeacher=new Teacher("tony");
        ITeacher teacher = (ITeacher) Proxy.newProxyInstance(realTeacher.getClass().getClassLoader(),
                realTeacher.getClass().getInterfaces(), new MyInvocationHandler(realTeacher));
        teacher.lecture("语文");
    }
}
