package com.zhou.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageQueue<T> {
    private T[] queue;
    private AtomicInteger input;//写入位置记录，初始0，value为待写入index
    private Map<String,Integer> output;//读取位置记录，初始-1，value为最后读取index
    private Map<String,Integer> ack;//消费确认，记录内容为消息待确认的偏移量
    public MessageQueue(int capacity){
        queue= (T[]) new Object[capacity];
        input=new AtomicInteger();
        output=new HashMap<>();
        ack=new HashMap<>();
    }
    synchronized public boolean put(T t){
        //写入指针到队尾，做queue内容移动处理，更新写入和读取位置
        if(input.get()>=queue.length){
            //判度胺copy起始位置
            int last=queue.length;
            for (Map.Entry<String, Integer> entry : output.entrySet()) {
                if(entry.getValue()<last){
                    last=entry.getValue();
                }
            }
            //消费者无消费记录或者消费记录为-1，队列满无法移动，写入消息失败
            if(last==queue.length||last==-1){
                return false;
            }
            //copy数组
            T[] queue2= (T[]) new Object[queue.length];
            System.arraycopy(queue,last+1,queue2,0,queue.length-(last+1));
            queue=queue2;
            //更新写入位置
            input.set(queue.length-(last+1));
            //更新读取位置
            for (Map.Entry<String, Integer> entry : output.entrySet()) {
                output.put(entry.getKey(),entry.getValue()-(last+1));
            }
        }
        //消息入队列
        queue[input.get()]=t;
        input.incrementAndGet();
        return true;
    }

    /**
     * 获取消息
     * @param consumer 消费者名称
     * @param n 获取数量
     * @param autoAck 是否自动确认
     * @return
     */
    public List<T> get(String consumer, int n, boolean autoAck){
        //获取位置记录
        Integer integer = output.get(consumer);
        //没有默认-1
        if(integer==null){
            integer=-1;
        }
        //获取写入位置记录
        int i = input.get();
        //如果读取位置下n个位置等于或超过写入位置，则已在队尾，获取失败，返回null
        if((integer+n)>=i){
            return null;
        }
        if(autoAck){
            //否则读取位置+n
            output.put(consumer,integer+n);
        }else{
            //否则记录消费确认偏移量
            ack.put(consumer,n);
        }
        //返回获取的消息
        List<T> result=new ArrayList<>();
        for (int l = 0; l < n; l++) {
            result.add(queue[integer+1+l]);
        }
        return result;
    }

    /**
     * 消费确认
     * @param consumer 消费者名称
     */
    public void ack(String consumer){
        Integer i = ack.get(consumer);
        if(i!=null&&i>0){
            Integer current = output.get(consumer);
            //需要考虑第一次消费的情况，此时消费位置记录不存在
            if(current==null){
                current=-1;
            }
            output.put(consumer,current+i);
            ack.remove(consumer);
        }
    }
}
