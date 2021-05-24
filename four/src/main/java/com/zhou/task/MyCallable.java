package com.zhou.task;

import com.zhou.work.Stop;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    public Integer call() throws Exception {
        return Stop.sum();
    }
}
