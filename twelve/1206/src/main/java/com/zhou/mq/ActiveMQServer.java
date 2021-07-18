package com.zhou.mq;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQServer {

    public static void main(String[] args) {
        // 尝试用java代码启动一个ActiveMQ broker server
        // 然后用前面的测试demo代码，连接这个嵌入式的server
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        try {
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
