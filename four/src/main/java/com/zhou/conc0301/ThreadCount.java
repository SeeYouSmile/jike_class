package com.zhou.conc0301;

public class ThreadCount {
    public static void main(String[] args) throws InterruptedException {
        //System.out.println("system："+Thread.currentThread().getThreadGroup().getParent());
        Thread.currentThread().getThreadGroup().getParent().list();
        //Thread[Monitor Ctrl-Break,5,main] idea特有线程

//        System.out.println("main："+Thread.currentThread().getThreadGroup());
//        Thread.currentThread().getThreadGroup().list();
    }
}
