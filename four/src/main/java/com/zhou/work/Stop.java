package com.zhou.work;

import com.zhou.task.MyCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 */
public class Stop {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        ExecutorService service= Executors.newFixedThreadPool(1);
        Future<Integer> future = service.submit(new MyCallable());
        System.out.println("异步计算结果为："+future.get());
        System.out.println("使用时间："+(System.currentTimeMillis()-start)+"ms");
        System.exit(0);
    }

    public static int sum(){
        return fibo(36);
    }

    private static int fibo(int a){
        if(a<2){
            return 1;
        }else{
            return fibo(a-1)+fibo(a-2);
        }
    }
}
