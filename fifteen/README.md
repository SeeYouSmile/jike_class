15周毕业作业

1、(必做)分别用 100 个字以上的一段话，加上一幅图 (架构图或脑图)，总结自己
对下列技术的关键点思考和经验认识:

1)JVM

内存模型：JVM.png

运行于windows上基于栈的虚拟机，内存模型为栈内存、堆内存、meta区、直接内存区。
程序在运行时每个线程在栈上都是相互独立的，基本数据类型和对象引用类型都存在各自的栈上，实际对象则保存在堆内存上。
mate区（元数据区）使用本地内存存放类信息、方法信息，直接内存区则时虚拟机运行时自身需要。
堆内一般采用分代收集算法，分为新生代和老年代，其中新生代又分为eden、s0、s1三个区，默认比例为8：1：1，
一般创建的新对象进入eden区，当发生gc时把eden区和s1中的对象通过可达性分析是否存货，把存货的对象复制到s0区，
然后清空eden和s1，再对调s0和s1的引用名称，当对象多次未被回收时则进入老年代。
新生代的算法一般有复制算法、标记清楚算法，老年代的算法一般有标记整理算法。

2)NIO

BIO（Blocking IO）再读取网络数据时必需要等待对方把数据发送过来或者超时，这样会影响线程内的其它工作。
NIO（Non-Blocking IO）则是轮询尝试去获取数据，没有获取到则先处理其它事情，完成后再去尝试，让程序不再等待。
基于NIO的Netty使用EventLoop去轮询获取数据，使用零拷贝技术直接使用堆外内存进行socket读写减少IO资源浪费，
主从Reactor多线程模型应对高并发场景。

3) 并发编程

提高程序效率但是要注意线程安全问题。

脑图：并发编程.png

4)Spring 和 ORM 等框架

bean生命周期：
构造函数->依赖注入->BeanNameAware->BeanFactoryAware->ApplicationContextAware->BeanPostProcessor前置方法
->InitializingBean->自定义init方法->BeanPostProcessor后置方法->使用bean->DisposableBean->自定义destroy方法

orm对象映射关系，通过操作对象的方式来操作数据库。可以解决数据库和程序之间异构性的问题。

5)MySQL 数据库和 SQL

索引就是表结构，除了主键索引外还可以自定义索引。索引一般使用B+tree结构，分为多层结构，底层为叶子节点（保存有该索引的所有信息），
上层非终端节点仅保存其子节点中最小和最大索引信息。维护索引结构代价较大，使得新增和修改会损失些性能，但是能换来查询效率的极大提高（空间换时间）。

mysql的存储引擎有两种，分别为myisam和innodb，前者不支持事务但是性能好，后者则支持事务。

日志：
binlog sql日志文件，记录有所有修改的记录，主从复制根据此文件同步。
undolog 撤销日志文件，记录事务回滚时的操作。
redolog 重做日志，确保事务的持久性，防止事务提交后数据未刷新到磁盘就掉电或崩溃。

mvcc机制（多版本并发控制）：
减少锁的使用，增加并发能力，在每行数据有3个隐藏列：
DB_TRX_ID：最后更新该行的事务ID
DB_ROLL_PTR：回滚指针，指向回滚段中写入的undolog记录，也就是这条记录的上一个版本
DB_ROW_ID：聚簇索引
当有update语句来修改行记录时，会先对行加排它锁，然后复制该行记录到undolog，修改完成后同时修改事务ID为当前事务ID，最后释放锁。
当有select语句（快照读）进来的的时候，只能看见事务ID小于等于当前事务ID的记录。

事务的特性：原子性、一致性、隔离性、持久性。
数据库事务的隔离级别由低到高分为4种：（mysql的默认隔离级别为不可重复读）
1.读未提交：会产生脏读、重复度、幻读
2.读已提交：会产生重复度、幻读
3.不可重复度：会产生幻读
4.序列化：不会产生问题，但是效率极低

6) 分库分表

分为垂直拆分和水平拆分。
垂直拆分为把单个表的字段拆分为2个或多个表的方式，用于表内字段过多、部分字段非常用数据、业务新增字段等情况，有效减少表的复杂度，提高查询效率。
水平拆分为把单个表的内容拆分为多个库和多个表保存的方式，用于表内数据过多导致的查询效率低下。比如需将一个订单表差分为2个库一共10个表，
则可以取id余2来分库，取订单id余5来分表。

shardingsphere是一套开源的分布式数据库解决方案，可以按照自定义的规则来分库分表，有2种使用方式。
方式一：配置进项目当中，以spring为例，根据规则配置properties文件，根据配置文件来配置数据库分库分表方式，
然后直接当一般数据库使用即可。
方式二：可当作独立运行的数据库，官网下载apache-shardingsphere-5.0.0-alpha-shardingsphere-proxy-bin，
配置好配置文件启动后就可以当成一个独立的数据库来使用，当sql请求进来时会根据规则去指定的数据库和表执行sql。

7)RPC 和微服务

RPC远程调用过程，像调用本地方法一样调用远程方法。
调用过程：客户端需要将要调用的函数和参数包装成对象后以序列化的方式传送给服务端，
服务端把获取到的数据反序列化后获取需要调用的函数的参数，调用本地方法并把结果包装好后序列化返回，
客户端再把得到的结果反序列化后就得到了结果。
见图：RPC调用.png

微服务把大型的业务拆分为多个细粒度、轻量级、松散耦合的服务，一般通过API暴露自己的服务。
分散服务后，可以更好的搭建分布式的集群服务。轻量化后使得代码更容易开发和维护。
见图：微服务架构.png

8) 分布式缓存

redis基本数据结构：
1、字符串，可以存储int、string、byte[]，使用命令有set、get、del、exists、append、incr、decr、incrby、decyby。
2、散列，可以看成一个具有String Key和String Value的map容器，使用命令：hset、hget、hgetall、hdel、hlen、hkeys、hvals。
3、列表，相当于java中的linkedList，使用命令：lpush、rpush、lrange、lpop、rpop。
4、集合，相当于java中的set，除了set不能重复的原则外还可以对多个集合求差集、交集、并集，使用命令：sadd、srem、smembers、sismember、
sdiff、sinter、sunion - 差集、交集、并集。
5、有序集合，和集合相似都不允许重复的成员，但是有序集合的每个成员都有一个关联的分数，分数时可以重复的，使用命令：zadd、zscore、zcard、zrem、zrange。
redis高级数据结构：
1、Bitmaps：在string上的一组面向bit操作的集合，命令：setbit、getbit、bitop、bitcount、bitpos。
2、hyperloglogs：可以用来计数，误差小于1%，命令：pfadd、pfcount、pfmerge。
3、geo：用于地理位置的记录，并且可以计算2点间距离以及点半径内位置数量等。

获取锁的方式：set key value nx px 30000 对key加锁，并设置超时时间30000毫秒。
redis使用事务是不保证原子性的，如果命令错误则不会执行，如果是逻辑错误则会产生问题。

cluster分片：
通过一致性哈希的方式将数据分散到多个服务器节点，先设计16k个哈希槽，分配到多个redis-server，
存取时先对key使用crc16算法计算出一个值，对16k取模，放入哈希槽对应的redis-server中。

主从模式：主库负责更新，从库异步拉取数据负责读取。
1、使用命令slaveof ip:port 来使当前节点成为某个主节点的从库。
2、在配置文件中直接设置，启动后自动成为从库。

哨兵模式：在主从模式的基础上增加与节点数相同数量的哨兵，哨兵监听所有的节点，彼此之间也相互通信。
当主节点失去响应后，通过投票机制推举出一个从节点成为主节点，当失去响应的原主节点恢复时，
自动成为从节点，达到高可用的目的。

9) 分布式消息队列

基于网络的系统间通信方式，有四大作用：
1.异步通信：可以实现异步的消息通信
2.系统解耦：可以简化参与各方的复杂依赖关系
3.削峰平谷：可以再请求量很大的时候缓冲一下
4.可靠通信：某些情况下能保障消息的可靠性，甚至顺序

消息处理模式：
点对点（Queue）：消息发送者sender发送消息到queue，queue再发送到其中一个绑定到该queue的消费者receiver上，消息一旦消费就没有了。
发布订阅（Topic）：消息提供者Publisher发送消息到topic，topic再把消息发送给所有订阅该topic的消费者subscriber，消息记录会一直保存再topic中。

消息处理的保障：
At most once 至多一次：消息可能丢失但不会重复发送。
At least once 至少一次：消息不会丢失，但可能重复发送。
Exactly once 精确一次：每条消息肯定会被传输有且仅有一次。

消息处理的事务性：
通过确认机制实现事务性，可以被事务管理器管理，甚至可以支持XA。

见图：消息队列通用结构.png
