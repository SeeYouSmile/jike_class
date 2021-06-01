package com.zhou.five.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MyConfiguration.class)
public class MyAutoConfiguration {

}
