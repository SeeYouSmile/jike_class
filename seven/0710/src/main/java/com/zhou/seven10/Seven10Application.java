package com.zhou.seven10;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

@MapperScan("com.zhou.seven10.mapper")
@SpringBootApplication(exclude = {JtaAutoConfiguration.class})
public class Seven10Application {

    public static void main(String[] args) {
        SpringApplication.run(Seven10Application.class, args);
    }

}
