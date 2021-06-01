package com.zhou.five.jms;

import com.alibaba.fastjson.JSONObject;
import com.zhou.five.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class SendService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(Student student){
        jmsTemplate.send("zhou.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(JSONObject.toJSONString(student));
            }
        });
    }

}
