kafka在windows下可太难了，启动自带的zookeeper报“命令语法不正确”，只好启动自己的zookeeper
接着启动kafkaServer倒是没有问题，然后操作队列，创建队列的命令“kafka-topics.bat --zookeeper localhost:2181 --create --topic testk --partition 3 --replication-factor 1”，
然后又报“命令语法不正确”了，搞不下去了。