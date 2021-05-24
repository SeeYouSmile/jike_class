package com.zhou.work;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Stop8 {
    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        CountDownLatch countDownLatch=new CountDownLatch(1);
        List<Integer> list=new ArrayList<>();
        new Thread(()->{
            int sum = Stop.sum();
            list.add(sum);
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println("异步计算结果为："+list.get(0));
        System.out.println("使用时间："+(System.currentTimeMillis()-start)+"ms");
        System.exit(0);
    }
}
