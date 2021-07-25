package com.zhou.core;

import java.util.List;

public final class Mymq {
    private String topic;
    private int capacity;
    private MessageQueue<MymqMessage> queue;

    public Mymq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        queue = new MessageQueue(capacity);
    }

    public boolean send(MymqMessage mymqMessage){
        return queue.put(mymqMessage);
    }

    public List<MymqMessage> poll(String consumer,int n,boolean autoAck){
        return queue.get(consumer,n,autoAck);
    }

    public void ack(String consumer){
        queue.ack(consumer);
    }

    public String getTopic() {
        return topic;
    }

    public int getCapacity() {
        return capacity;
    }
}
