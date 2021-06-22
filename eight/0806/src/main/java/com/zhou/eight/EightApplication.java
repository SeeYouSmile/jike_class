package com.zhou.eight;

import com.zhou.eight.config.DatasourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DatasourceConfig.class})
public class EightApplication {

    public static void main(String[] args) {
        SpringApplication.run(EightApplication.class, args);
    }

}
