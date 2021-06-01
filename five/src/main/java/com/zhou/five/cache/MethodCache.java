package com.zhou.five.cache;

import com.zhou.five.pojo.ObjectCache;

import java.util.HashMap;
import java.util.Map;

public class MethodCache {

    private Map<String, ObjectCache> cache=new HashMap<>();

    public Object get(String key){
        ObjectCache objectCache = cache.get(key);
        if(objectCache==null){
            return null;
        }
        int cacheTime = objectCache.getCacheTime();
        long updateTime = objectCache.getUpdateTime();
        long currentTime = System.currentTimeMillis();
        //如果待获取的对象时间超时的话，移除缓存的对象并返回null
        if(currentTime-updateTime>cacheTime*1000){
            cache.remove(key);
            return null;
        }
        return objectCache.getValue();
    }
    public void set(String key,ObjectCache objectCache){
        cache.put(key,objectCache);
    }
}
