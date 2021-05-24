package com.zhou.work;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Stop7 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        List<Integer> list=new ArrayList<>();
        new Thread(()->{
            try {
                cyclicBarrier.await();
                System.out.println("异步计算结果为："+list.get(0));
                System.out.println("使用时间："+(System.currentTimeMillis()-start)+"ms");
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            int sum = Stop.sum();
            list.add(sum);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
