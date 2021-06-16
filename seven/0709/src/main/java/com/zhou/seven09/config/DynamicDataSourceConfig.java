package com.zhou.seven09.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zhou.seven09.datasource.DataSourceNames;
import com.zhou.seven09.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("datasource.first")
    public DataSource firstDatasource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("datasource.second")
    public DataSource secondDatasource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource firstDatasource, DataSource secondDatasource){
        Map<Object,Object> target=new HashMap<>();
        target.put(DataSourceNames.FIRST,firstDatasource);
        target.put(DataSourceNames.SECOND,secondDatasource);
        //默认第一个数据库
        return new DynamicDataSource(firstDatasource,target);
    }
}
