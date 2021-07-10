package io.kimmking.cache.controller;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private RedisClient redisClient;

    /**
     * 下单，随机生成订单号码
     */
    @GetMapping("/pub")
    public void pub(){
        RedisPubSubCommands<String, String> sync = redisClient.connectPubSub().sync();
        String s = UUID.randomUUID().toString();
        sync.publish("order",s);
        System.out.println("向频道order发送订单："+s);
    }
}
