package com.zhou.conc0302.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConditionDemo {
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    
    final Object[] items = new Object[20];
    int putptr, takeptr, count;
    
    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            // 当count等于数组的大小时，当前线程等待，直到notFull通知，再进行生产
            while (count == items.length) {//这里换成if也不会有影响
                System.out.println("full");
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
//            Thread.sleep(100);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            // 当count为0，进入等待，直到notEmpty通知，进行消费。
            if (count == 0) {//使用if也没问题
                System.out.println("empty");
                notEmpty.await();
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
//            Thread.sleep(100);
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}