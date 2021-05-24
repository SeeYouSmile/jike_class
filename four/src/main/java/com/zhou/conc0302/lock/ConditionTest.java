package com.zhou.conc0302.lock;

import java.util.Random;

public class ConditionTest {
    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();
        Random random = new Random();
        new Thread(()->{
            while(true){
                try {
                    int i = random.nextInt(100);
                    conditionDemo.put("apple"+i);
                    System.out.println(Thread.currentThread().getName()+" put apple"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while(true){
                try {
                    int i = random.nextInt(100);
                    conditionDemo.put("apple"+i);
                    System.out.println(Thread.currentThread().getName()+" put apple"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while(true){
                try {
                    Object take = conditionDemo.take();
                    System.out.println(Thread.currentThread().getName()+" take "+take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while(true){
                try {
                    Object take = conditionDemo.take();
                    System.out.println(Thread.currentThread().getName()+" take "+take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while(true){
                try {
                    Object take = conditionDemo.take();
                    System.out.println(Thread.currentThread().getName()+" take "+take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while(true){
                try {
                    Object take = conditionDemo.take();
                    System.out.println(Thread.currentThread().getName()+" take "+take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while(true){
                try {
                    Object take = conditionDemo.take();
                    System.out.println(Thread.currentThread().getName()+" take "+take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while(true){
                try {
                    Object take = conditionDemo.take();
                    System.out.println(Thread.currentThread().getName()+" take "+take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
