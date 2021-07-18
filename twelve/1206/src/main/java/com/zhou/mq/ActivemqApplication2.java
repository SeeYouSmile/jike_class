package com.zhou.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ActivemqApplication2 {

    public static void main(String[] args) {

        //定义queue
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("zhou.queue");

        testDestination2(activeMQQueue);

    }

    public static void testDestination2(Destination destination) {
        try {
            // 创建连接和会话
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
            conn.start();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);


            // 创建消费者
            MessageConsumer consumer = session.createConsumer( destination );
            final AtomicInteger count = new AtomicInteger(0);
            MessageListener listener = new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        // 打印所有的消息内容
                        // Thread.sleep();
                        System.out.println(count.incrementAndGet() + " => receive from " + destination.toString() + ": " + message);
                        // message.acknowledge(); // 前面所有未被确认的消息全部都确认。

                    } catch (Exception e) {
                        e.printStackTrace(); // 不要吞任何这里的异常，
                    }
                }
            };
            // 绑定消息监听器
            consumer.setMessageListener(listener);

            //consumer.receive()

            // 创建生产者，生产10个消息
            MessageProducer producer = session.createProducer(destination);
            int index = 0;
            while (index++ < 10) {
                TextMessage message = session.createTextMessage(index + " message.");
                producer.send(message);
            }

            Thread.sleep(2000);
            session.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
