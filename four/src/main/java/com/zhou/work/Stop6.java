package com.zhou.work;

import java.util.ArrayList;
import java.util.List;

public class Stop6 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Object lock=new Object();
        List<Integer> list=new ArrayList<>();
        new Thread(()->{
            synchronized (lock){
                try {
                    if(list.size()==0){
                        lock.wait();
                    }
                    System.out.println("异步计算结果为："+list.get(0));
                    System.out.println("使用时间："+(System.currentTimeMillis()-start)+"ms");
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            int sum = Stop.sum();
            list.add(sum);
            synchronized (lock){
                lock.notifyAll();
            }
        }).start();
    }
}
