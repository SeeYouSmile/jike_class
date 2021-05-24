package com.zhou.work;

import com.zhou.task.MyCallable;

import java.util.concurrent.*;

public class Stop2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long start=System.currentTimeMillis();
        ExecutorService service= Executors.newFixedThreadPool(1);
        Future<Integer> future = service.submit(new MyCallable());
        System.out.println("异步计算结果为："+future.get(200, TimeUnit.MILLISECONDS));
        System.out.println("使用时间："+(System.currentTimeMillis()-start)+"ms");
        System.exit(0);
    }
}
