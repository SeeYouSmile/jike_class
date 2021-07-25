package com.zhou.server.service;

import com.zhou.core.Mymq;
import com.zhou.core.MymqMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MymqServer {

    @Value("${mymq.capacity}")
    private int capacity;
    private final Map<String, Mymq> server=new HashMap<>();

    public boolean send(String topic,Object body){
        Mymq mymq = server.get(topic);
        if(mymq==null){
            mymq = new Mymq(topic, capacity);
            server.put(topic,mymq);
        }
        MymqMessage tMymqMessage = new MymqMessage(topic, body);
        return mymq.send(tMymqMessage);
    }

    public List<Object> poll(String topic,String consumer,int n,boolean autoAck){
        Mymq mymq = server.get(topic);
        if(mymq==null){
            return null;
        }
        List<MymqMessage> poll = mymq.poll(consumer,n,autoAck);
        if(poll==null){
            return null;
        }
        List<Object> bodies=new ArrayList<>();
        for (MymqMessage mymqMessage : poll) {
            bodies.add(mymqMessage.getBody());
        }
        return bodies;
    }

    public void ack(String topic,String consumer){
        Mymq mymq = server.get(topic);
        if(mymq!=null){
            mymq.ack(consumer);
        }
    }
}
