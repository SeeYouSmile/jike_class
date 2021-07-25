Week13 作业题目：

1.（必做）搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。

详见文件夹1301

2.（选做）安装 kafka-manager 工具，监控 kafka 集群状态。

3.（挑战☆）演练本课提及的各种生产者和消费者特性。

4.（挑战☆☆☆）Kafka 金融领域实战：在证券或者外汇、数字货币类金融核心交易系统里，对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用 mq 来实现订单定序，然后将订单发送给撮合模块。

收单：请实现一个订单的 rest 接口，能够接收一个订单 Order 对象；
定序：将 Order 对象写入到 kafka 集群的 order.usd2cny 队列，要求数据有序并且不丢失；
撮合：模拟撮合程序（不需要实现撮合逻辑），从 kafka 获取 order 数据，并打印订单信息，要求可重放, 顺序消费, 消息仅处理一次。
5.（选做）自己安装和操作 RabbitMQ，RocketMQ，Pulsar，以及 Camel 和 Spring Integration。

6.（必做）思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。

详见文件夹1306，完成了第二版和第三版的功能
二版思路：
自定义queue队列，使用数组存放消息，使用AtomicInteger记录写入位置，Map<String,Integer>记录每个消费者的读取位置。
消息队列的oom问题改进一点点，在消息入队前判断写入位置是否已到队尾，是则循环遍历消费者读取位置，
判断是否可移动数组以及计算移动的长度位置信息，不能移动返回入队失败，可以移动则移动未消费队列至队头（移动采用复制处一个新队列，然后把新队列指向原队列的引用）
并更新入队位置和消费者读取位置信息；否则返回入队失败。
在自定义queue里添加确认消息偏移量记录map，用来记录每个消费者待确认的消息消费偏移量，在确认后更新消费者读取位置并移除待消费偏移量记录。
三版思路：
除了核心类之外，拆分成3个子项目（server、producer、consumer），server作为提供queue的服务，producer和consumer通过http与server进行通讯，
获取消息方法新增获取数量参数，根据数量返回消息列表。

第一个版本-内存 Queue
1、基于内存 Queue 实现生产和消费 API（已经完成）
1）创建内存 BlockingQueue，作为底层消息存储
2）定义 Topic，支持多个 Topic
3）定义 Producer，支持 Send 消息
4）定义Consumer，支持Poll消息

第二个版本：自定义 Queue
2、去掉内存 Queue，设计自定义 Queue，实现消息确认和消费 offset
1）自定义内存 Message 数组模拟 Queue。 2）使用指针记录当前消息写入位置。
3）对于每个命名消费者，用指针记录消费位置。

第三个版本：基于 SpringMVC 实现 MQServer
3、拆分 broker 和 client（包括 producer 和 consumer） 1）将 Queue 保存到 web server 端 2）设计消息读写 API 接口，确认接口，提交 offset 接口
3）producer 和 consumer 通过 httpclient 访问 Queue
4）实现消息确认，offset 提交
5）实现 consumer 从 offset 增量拉取

第四个版本：功能完善 MQ
4、增加多种策略（各条之间没有关系，可以任意选择实现）
1）考虑实现消息过期，消息重试，消息定时投递等策略
2）考虑批量操作，包括读写，可以打包和压缩
3）考虑消息清理策略，包括定时清理，按容量清理、LRU 等 4）考虑消息持久化，存入数据库，或 WAL 日志文件，或 BookKeeper
5）考虑将 spring mvc 替换成 netty 下的 TCP 传输协议，rsocket/websocket

第五个版本：体系完善 MQ
5、对接各种技术（各条之间没有关系，可以任意选择实现）
1）考虑封装 JMS 1.1 接口规范
2）考虑实现 STOMP 消息规范
3）考虑实现消息事务机制与事务管理器
4）对接 Spring
5）对接 Camel 或 Spring Integration
6）优化内存和磁盘的使用

7.（挑战☆☆☆☆☆）完成所有其他版本的要求。期限一年。