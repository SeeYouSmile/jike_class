第五周作业

1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。

com.zhou.five.JavaAOP.java

2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。

com.zhou.five.SpringDemo1.java

3.（选做）实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。
4.（选做，会添加到高手附加题）
4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；
4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；
4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；
4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。

5.（选做）总结一下，单例的各种写法，比较它们的优劣。
6.（选做）maven/spring 的 profile 机制，都有什么用法？

可以自定义配置信息，在不同条件下使用对应的配置方案。
对于xml配置，可以使用beans标签，设置profile值为对应properties，里面包裹需要在该环境下加载的bean
对于配置类，可以在类上或者bean方法上添加@Profile注解，里面的值填入对应的properties

7.（选做）总结 Hibernate 与 MyBatis 的各方面异同点。

Hibernate：全自动orm框架
优点：会自动生产sql语句，无需写sql
缺点：难以dba
MyBatis：半自动orm框架
优点：能直观体现sql内容，方便dba
缺点：需要自己写sql语句，繁琐，可以使用mybatis-plus插件

8.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

com.zhou.five.FiveApplication.java
bean配置类：com.zhou.five.config.MyAutoConfigiguration.java
因为有数据库和ActiveMQ的配置，所以需要者2个都有才行

9.（选做）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。
10.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
1）使用 JDBC 原生接口，实现数据库的增删改查操作。

com.zhou.five.jdbc.JDBCDemo1.java
数据库为在resources下的test.sql，下同

2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。

com.zhou.five.jdbc.JDBCDemo2.java

3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub。

com.zhou.five.jdbc.JDBCDemo3.java

附加题（可以后面上完数据库的课再考虑做）：
(挑战) 基于 AOP 和自定义注解，实现 @MyCache(60) 对于指定方法返回值缓存 60 秒。

注解：com.zhou.five.annotation.MyCache.java
自定义缓存：com.zhou.five.cache.MethodCache.java
AOP：com.zhou.five.aop.Aop2.java
对Klass类的dong方法添加了注解，设定缓存时间5秒
SpringDemo1.java演示了使用

(挑战) 自定义实现一个数据库连接池，并整合 Hibernate/Mybatis/Spring/SpringBoot。
(挑战) 基于 MyBatis 实现一个简单的分库分表 + 读写分离 + 分布式 ID 生成方案。