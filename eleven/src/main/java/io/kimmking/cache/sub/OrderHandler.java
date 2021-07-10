package io.kimmking.cache.sub;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderHandler {

    @Resource
    private RedisClient redisClient=RedisClient.create("redis://localhost:6379");

    {
        RedisPubSubCommands<String, String> sync = redisClient.connectPubSub().sync();
        sync.getStatefulConnection().addListener(new RedisPubSubListener<String, String>() {
            @Override
            public void message(String s, String s2) {
                System.out.println("收到订阅"+s+"频道发送的订单："+s2);
            }

            @Override
            public void message(String s, String k1, String s2) {

            }

            @Override
            public void subscribed(String s, long l) {

            }

            @Override
            public void psubscribed(String s, long l) {

            }

            @Override
            public void unsubscribed(String s, long l) {

            }

            @Override
            public void punsubscribed(String s, long l) {

            }
        });
        sync.subscribe("order");
    }
}
