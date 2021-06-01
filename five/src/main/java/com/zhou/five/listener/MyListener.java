package com.zhou.five.listener;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class MyListener {
    @JmsListener(destination = "zhou.queue",containerFactory = "queueListener")
    public void onMessage(Message message){
        System.out.println(JSONObject.toJSONString(message));
    }
}
