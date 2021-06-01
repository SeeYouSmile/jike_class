package com.zhou.five;

import com.zhou.five.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiveApplication.class, args);
    }

    @Qualifier("getStudent")
    @Autowired
    Student student;

    @Bean
    public void print(){
        //需要加载的bean很多，打印的信息有点多，要慢慢找一下，最长的那个就是
        System.out.println(student);
    }

}
