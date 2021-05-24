package com.zhou.work;

import java.util.HashMap;
import java.util.Map;

public class Stop3 {
    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        Map<String,Integer> map=new HashMap<>();
        new Thread(()->{
            int sum = Stop.sum();
            map.put("sum",sum);
        }).start();
        Thread.sleep(200);
        System.out.println("异步计算结果为："+map.get("sum"));
        System.out.println("使用时间："+(System.currentTimeMillis()-start)+"ms");
        System.exit(0);
    }
}
