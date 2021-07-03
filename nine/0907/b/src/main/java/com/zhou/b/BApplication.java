package com.zhou.b;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring-dubbo.xml"})
@MapperScan("com.zhou.common.mapper")
public class BApplication {

    public static void main(String[] args) {
        SpringApplication.run(BApplication.class, args);
    }

}
