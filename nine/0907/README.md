hmily+dubbo实现tcc分布式事务

数据库结构文件位于0907/src/main/java/resources/0907_db.sql
内有3个表，分别为user（用户表）、freeze（冻结表）、transfer（交易记录表）
使用sql文件创建2个数据库db_0和db_1，除此外还需创建一个名字为hmily的空数据库，用于hmily框架自动记录调度情况

0907下有3个子项目：common、service-a、service-b
common为公共组件包含常量类、传输类、mapper、pojo、service
service-a和service-b为实际应用项目，需要有zookeeper注册中心（这里配置的是127.0.0.1:2181）
2个服务都有对外暴露接口：
service-a：http://localhost:8091/transfer/a2b
参数：{
           "user_id_from":"1",//发起方用户id（需在数据库db_0中）
           "user_id_to":"2",//对手方用户id（需在数据库db_1中）
           "money":2,//交易金额
           "type_from":"USD",//发起方币种（大小写皆可）
           "type_to":"CNY"//对手方币种（大小写皆可）
      }
service-b：http://localhost:8092/transfer/b2a
参数：{
         "user_id_from":"2",//发起方用户id（需在数据库db_1中）
         "user_id_to":"1",//对手方用户id（需在数据库db_0中）
         "money":1,//交易金额
         "type_from":"CNY",//发起方币种（大小写皆可）
         "type_to":"USD"//对手方币种（大小写皆可）
        }

交易完成后会在发起方的数据库创建一条交易记录
如果中途发生中断，hmily框架会自动重新尝试（从中断位置，比如在confirm或者cancel时断了，会在服务恢复时自动重试）