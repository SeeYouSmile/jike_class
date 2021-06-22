DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `status` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

//自动雪花生成order_id计算分表的时候只会使用到0和1这两个表
insert into `t_order` (`user_id`,`status`) values (1,'ok'),(2,'fail');
//手动插入order_id就会正确计算
insert into `t_order` (`order_id`,`user_id`,`status`) values (3,3,'ok');
//参数齐全，真实只执行一条sql
update `t_order` set status='fail' where `order_id`=3 and 'user_id'=3;
//从所有库表查询
select * from `t_order`;
//从ds_1库查询所有表
select * from `t_order` where `user_id`=1;
//从所有库的表3去删除
delete from `t_order` where `order_id`=3;