package com.zhou.five;

import com.zhou.five.aop.ISchool;
import com.zhou.five.cache.MethodCache;
import com.zhou.five.pojo.Klass;
import com.zhou.five.pojo.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo1 {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123.toString());
        student123.print();

        Student student100 = (Student) context.getBean("student100");
        System.out.println(student100.toString());

        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1);
        System.out.println("Klass代理后的实际类型："+class1.getClass());
        System.out.println("Klass代理后的实际类型是否是Klass子类："+(class1 instanceof Klass));

        ISchool school = context.getBean(ISchool.class);
        System.out.println(school);
        System.out.println("ISchool代理后的实际类型："+school.getClass());

        school.ding();
        class1.dong();

        MethodCache cache = (MethodCache) context.getBean("cache");
        Object dong = cache.get("dong");
        System.out.println("缓存读取dong方法的返回："+dong);

        Thread.sleep(5000);

        dong = cache.get("dong");
        System.out.println("过5秒从缓存读取dong方法的返回："+dong);
    }
}
