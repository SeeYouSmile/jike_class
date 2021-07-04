package com.zhou.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@ImportResource({"classpath:spring-dubbo.xml"})
@MapperScan({"com.zhou.common.mapper"})
public class ApplicationA {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationA.class,args);
    }
}
