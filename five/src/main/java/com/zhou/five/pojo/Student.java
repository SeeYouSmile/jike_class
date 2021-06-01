package com.zhou.five.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix = "stu")
public class Student implements Serializable , BeanNameAware , ApplicationContextAware {
    private int id;
    private String name;

    private String a;

    private String beanName;
    private ApplicationContext applicationContext;

    public void print(){
        System.out.println("beanName->"+beanName);
        System.out.println("applicationContext->");
        for (int i = 0; i < applicationContext.getBeanDefinitionCount(); i++) {
            System.out.println(applicationContext.getBeanDefinitionNames()[i]);
        }
    }

    @Override
    public void setBeanName(String s) {
        beanName=s+"-"+System.currentTimeMillis();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
