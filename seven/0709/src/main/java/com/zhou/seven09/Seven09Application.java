package com.zhou.seven09;

import com.zhou.seven09.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@MapperScan("com.zhou.seven09.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class Seven09Application {

    public static void main(String[] args) {
        SpringApplication.run(Seven09Application.class, args);
    }

}
