package com.zhou.conc0301;

public class DaemonThread {
    
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
                try {
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                Thread t = Thread.currentThread();
                System.out.println("当前线程:" + t.getName());
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true);//守护线程，当主线程结束时，守护线程也会退出，所以不会打印线程名称
        thread.start();

//        Thread.sleep(2000);
    }
    
    
}
