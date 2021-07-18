Week12 作业题目：

1.（必做）配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

详见文件夹1201

主从复制2种方法：
一：直接配置2个不同端口的conf配置文件，启动后，在其中一个库使用slaveof [host] [port]来指定主库
二：在配置文件种添加slaveof [host] [port]来指定主库，启动后自动就成为从库了

高可用：
在启动主从复制后，再使用命令redis-server sentinel0.conf --sentinel 和 redis-server sentinel1.conf --sentinel
分别启动2个哨兵服务来监控，这时如果主库（6379）down了，过10秒就会把6380推举为主库，当6379重启后自动成为从库

Cluster集群：
详见/1201/redis-cluster-demo文件夹
1.准备ruby环境
2.准备6个redis的配置文件（端口配置为7001、7002、7003、7004、7005、7006）
3.下载并安装redis的ruby驱动（redis-4.3.1.gem），安装命令：gem install --local ?/redis-4.3.1.gem(这里为redis-4.3.1.gem的路径)
4.启动6个redis服务（确认没有rdb和aof文件，有就删除）
5.删除6个redis目录下自动创建的nodes.conf文件
6.使用redis-trib.rb文件来创建redis集群，命令：redis-trib.rb --replicas 1 127.0.0.1:7001 127.0.0.1:7002 
127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006

启动成功后可以使用使用redis客户端连接任意一个接口来使用集群（不要忘记加-c），命令：redis-cli -p 7001 -c
cluster nodes 查看集群节点信息
cluster info 查看集群状态信息
使用set、get命令设置和获取值时会自动跳转到对应的存储接口上


2.（选做）练习示例代码里下列类中的作业题:
08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java

3.（选做☆）练习 redission 的各种功能。

4.（选做☆☆）练习 hazelcast 的各种功能。

5.（选做☆☆☆）搭建 hazelcast 3 节点集群，写入 100 万数据到一个 map，模拟和演 示高可用。

6.（必做）搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。

详见文件夹1206
topic模式：ActivemqApplication.java
queue模式：ActivemqApplication2.java

7.（选做）基于数据库的订单表，模拟消息队列处理订单：

一个程序往表里写新订单，标记状态为未处理 (status=0);
另一个程序每隔 100ms 定时从表里读取所有 status=0 的订单，打印一下订单数据，然后改成完成 status=1；
（挑战☆）考虑失败重试策略，考虑多个消费程序如何协作。
8.（选做）将上述订单处理场景，改成使用 ActiveMQ 发送消息处理模式。

9.（选做）使用 java 代码，创建一个 ActiveMQ Broker Server，并测试它。

详见文件夹1206
ActiveMQServer.java

10.（挑战☆☆）搭建 ActiveMQ 的 network 集群和 master-slave 主从结构。

11.（挑战☆☆☆）基于 ActiveMQ 的 MQTT 实现简单的聊天功能或者 Android 消息推送。

12.（挑战☆）创建一个 RabbitMQ，用 Java 代码实现简单的 AMQP 协议操作。

13.（挑战☆☆）搭建 RabbitMQ 集群，重新实现前面的订单处理。

14.（挑战☆☆☆）使用 Apache Camel 打通上述 ActiveMQ 集群和 RabbitMQ 集群，实现所有写入到 ActiveMQ 上的一个队列 q24 的消息，自动转发到 RabbitMQ。

15.（挑战☆☆☆）压测 ActiveMQ 和 RabbitMQ 的性能。