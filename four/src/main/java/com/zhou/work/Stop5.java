package com.zhou.work;

import java.util.ArrayList;
import java.util.List;

public class Stop5 {
    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        List<Integer> list=new ArrayList<>();
        new Thread(()->{
            int sum = Stop.sum();
            list.add(sum);
        }).start();
        while(list.size()==0){
            Thread.sleep(10);
        }
        System.out.println("异步计算结果为："+list.get(0));
        System.out.println("使用时间："+(System.currentTimeMillis()-start)+"ms");
        System.exit(0);
    }
}
