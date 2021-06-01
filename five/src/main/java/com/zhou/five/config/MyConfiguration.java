package com.zhou.five.config;

import com.zhou.five.pojo.Klass;
import com.zhou.five.pojo.School;
import com.zhou.five.pojo.Student;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MyConfiguration {

    @Bean
    public Student getStudent(){
        return new Student();
    }

    @Bean(name = "student123")
    public Student getStudent123(){
        return new Student();
    }

    @Bean(name = "student100")
    public Student getStudent100(){
        return new Student();
    }

    @Bean
    public Klass getKlass(){
        return new Klass();
    }

    @Bean
    public School getSchool(){
        return new School();
    }

    @Bean
    public ActiveMQConnectionFactory getActiveMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate getJmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(getActiveMQConnectionFactory());
        return jmsTemplate;
    }

    @Bean(name = "queueListener")
    public JmsListenerContainerFactory jmsListenerContainerFactory(){
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(getActiveMQConnectionFactory());
        factory.setPubSubDomain(false);
        return factory;
    }

}
