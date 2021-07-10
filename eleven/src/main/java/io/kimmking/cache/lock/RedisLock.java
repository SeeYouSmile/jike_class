package io.kimmking.cache.lock;

import io.lettuce.core.RedisClient;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;

public class RedisLock {

    private static RedisClient redisClient=RedisClient.create("redis://localhost:6379");

    /**
     * 获取锁
     * @param lock 锁名称
     * @param seconds 锁定时间（秒），小于等于0的话，无时间限制
     * @return
     */
    public static boolean lock(String lock,long seconds){
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        String s = connect.sync().get(lock);
        if(s!=null){
            return false;
        }
        if(seconds<=0){
            String set = connect.sync().set(lock, lock);
            return "OK".equals(set);
        }else{
            String set = connect.sync().set(lock, lock, new SetArgs().ex(seconds));
            return "OK".equals(set);
        }
    }

    /**
     * 释放锁
     * @param lock 锁名称
     */
    public static void unLock(String lock){
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        connect.sync().del(lock);
    }
}
