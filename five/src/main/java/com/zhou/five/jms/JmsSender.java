package com.zhou.five.jms;

import com.zhou.five.pojo.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsSender {
    public static void main(String[] args) {
        Student student = new Student(2, "li-si",null, null, null);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springjms-sender.xml");
        SendService sendService = context.getBean(SendService.class);
        sendService.send(student);
        System.out.println("send success,please visit http://localhost:8161/admin to see it");
    }
}
