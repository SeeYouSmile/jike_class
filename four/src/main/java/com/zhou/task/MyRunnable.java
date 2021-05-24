package com.zhou.task;

import com.zhou.work.Stop;

public class MyRunnable implements Runnable{

    private CallBack callBack;

    public MyRunnable(CallBack callBack) {
        this.callBack = callBack;
    }

    public void run() {
        int sum = Stop.sum();
        if(callBack!=null){
            callBack.back(sum);
        }
    }

    interface CallBack{
        void back(int sum);
    }
}
