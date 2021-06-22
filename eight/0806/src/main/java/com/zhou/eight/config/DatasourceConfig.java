package com.zhou.eight.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatasourceConfig {

    private static final String XA_DS_CLASS_NAME="com.mysql.cj.jdbc.MysqlXADataSource";

    @Bean(name = "db_0")
    @Primary
    public DataSource dataSource_0(Environment environment){
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        Properties properties = build(environment, "spring.datasource.db_0.");
        atomikosDataSourceBean.setXaDataSourceClassName(XA_DS_CLASS_NAME);
        atomikosDataSourceBean.setUniqueResourceName("db_0");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    @Bean(name = "db_1")
    public DataSource dataSource_1(Environment environment){
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        Properties properties = build(environment, "spring.datasource.db_1.");
        atomikosDataSourceBean.setXaDataSourceClassName(XA_DS_CLASS_NAME);
        atomikosDataSourceBean.setUniqueResourceName("db_1");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    @Bean(destroyMethod = "close",initMethod = "init")
    public UserTransactionManager userTransactionManager(){
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean(name = "xatm")
    public JtaTransactionManager jtaTransactionManager(){
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager());
        return jtaTransactionManager;
    }

    private Properties build(Environment environment,String prefix){
        Properties properties = new Properties();
        properties.put("url",environment.getProperty(prefix+"url"));
        properties.put("user",environment.getProperty(prefix+"username"));
        properties.put("password",environment.getProperty(prefix+"password"));
        return properties;
    }
}
