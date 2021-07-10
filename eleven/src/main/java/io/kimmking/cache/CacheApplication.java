package io.kimmking.cache;

import io.kimmking.cache.lock.RedisLock;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "io.kimmking.cache")
@MapperScan("io.kimmking.cache.mapper")
@EnableCaching
public class CacheApplication {

    final String LOCK="lock";
    final long EX_TIME=1;//锁定时间1秒
    int amount=5;

    public void test(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = Thread.currentThread().getName();
        if(RedisLock.lock(LOCK, EX_TIME)){
            System.out.println(name+"|获取到锁");
            System.out.println(name+"|剩余库存："+amount);
            amount--;
            System.out.println(name+"|扣减后剩余库存："+amount);
            RedisLock.unLock(LOCK);
        }else{
            System.out.println(name+"|获取锁失败");
        }
    }

	public static void main(String[] args){
		SpringApplication.run(CacheApplication.class, args);

        CacheApplication cacheApplication = new CacheApplication();
        Thread thread1 = new Thread(() -> cacheApplication.test(0));
        Thread thread2 = new Thread(() -> cacheApplication.test(0));
        Thread thread3 = new Thread(() -> cacheApplication.test(1000));
        Thread thread4 = new Thread(() -> cacheApplication.test(2000));
        Thread thread5 = new Thread(() -> cacheApplication.test(500));
        thread1.setName("thread-1");
        thread2.setName("thread-2");
        thread3.setName("thread-3");
        thread4.setName("thread-4");
        thread5.setName("thread-5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }

}
