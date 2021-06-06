/*
Navicat MySQL Data Transfer

Source Server         : mysql56
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : baotao

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2021-06-06 09:25:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL COMMENT '用户id',
  `shop_id` varchar(30) NOT NULL COMMENT '商店id',
  `commodity_id` varchar(30) NOT NULL COMMENT '商品id',
  `commodity_name` varchar(255) NOT NULL COMMENT '商品名称',
  `specifications_id` varchar(30) NOT NULL COMMENT '规格id',
  `specifications_name` varchar(255) NOT NULL COMMENT '规格名称',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `real_price` decimal(10,2) NOT NULL COMMENT '实际价格',
  `free_price` decimal(10,2) NOT NULL COMMENT '优惠价格',
  `buy` int(11) NOT NULL COMMENT '购买数量',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`user_id`,`shop_id`,`commodity_id`,`specifications_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表，commodity_updatetime和specifications_updatetime用于查看是否商品有修改或过期';

-- ----------------------------
-- Table structure for commodity_category_back
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category_back`;
CREATE TABLE `commodity_category_back` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_back_id` int(11) NOT NULL COMMENT '后台类目id',
  `parent_category_back_id` int(11) DEFAULT NULL COMMENT '父id',
  `category_back_name` varchar(30) NOT NULL COMMENT '后台类目名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标记，1为删除，其它为存在',
  `enable` int(11) DEFAULT NULL COMMENT '启用标志，1为停用，其它为启用',
  PRIMARY KEY (`id`),
  KEY `category_back_id` (`category_back_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品后台类目表';

-- ----------------------------
-- Table structure for commodity_category_front
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category_front`;
CREATE TABLE `commodity_category_front` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_front_id` int(11) NOT NULL COMMENT '前台类目id',
  `parent_category_front_id` int(11) DEFAULT NULL COMMENT '父id',
  `category_front_name` varchar(30) NOT NULL COMMENT '前台类目名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `category_back_id` int(11) NOT NULL COMMENT '后台类目id',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标记，1为删除，其它为存在',
  `enable` int(11) DEFAULT NULL COMMENT '启用标志，1为停用，其它为启用',
  PRIMARY KEY (`id`),
  KEY `category_front_id` (`category_front_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品前台类目表';

-- ----------------------------
-- Table structure for commodity_sku
-- ----------------------------
DROP TABLE IF EXISTS `commodity_sku`;
CREATE TABLE `commodity_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_id` varchar(30) NOT NULL COMMENT '商品id',
  `specifications_id` varchar(30) NOT NULL COMMENT '规格id',
  `specifications_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `stock` int(11) NOT NULL COMMENT '库存',
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标记，1为删除，其它为存在',
  `enable` int(11) DEFAULT NULL COMMENT '启用标志，1为停用，其它为启用',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `specifications_id` (`specifications_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品库存表';

-- ----------------------------
-- Table structure for commodity_spu
-- ----------------------------
DROP TABLE IF EXISTS `commodity_spu`;
CREATE TABLE `commodity_spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_id` varchar(30) NOT NULL COMMENT '商品id',
  `commodity_name` varchar(255) NOT NULL COMMENT '商品名称',
  `shop_id` varchar(30) NOT NULL COMMENT '商品id',
  `payment` int(11) NOT NULL COMMENT '付款数',
  `window_url` varchar(255) DEFAULT NULL COMMENT '展示图片',
  `category_back_id` int(11) NOT NULL COMMENT '后台类目id',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标记，1为删除，其它为存在',
  `enable` int(11) DEFAULT NULL COMMENT '启用标志，1为停用，其它为启用',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `commodity_id` (`commodity_id`,`shop_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性表';

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `order_id` varchar(30) NOT NULL COMMENT '订单编号',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `real_price` decimal(10,2) NOT NULL COMMENT '实际价格',
  `free_price` decimal(10,0) NOT NULL COMMENT '优惠价格',
  `status` int(11) NOT NULL COMMENT '订单状态，1-待付款，2-待发货，3-运输，4-完成',
  `payment` int(11) DEFAULT NULL COMMENT '付款方式',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标记，1为删除，其它为存在',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_shop
-- ----------------------------
DROP TABLE IF EXISTS `order_shop`;
CREATE TABLE `order_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(30) NOT NULL COMMENT '订单编号',
  `shop_id` varchar(30) NOT NULL COMMENT '商店id',
  `shop_name` varchar(255) NOT NULL COMMENT '商店名称',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `real_price` decimal(10,2) NOT NULL COMMENT '实际价格',
  `free_price` decimal(10,2) NOT NULL COMMENT '优惠价格',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`order_id`,`shop_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单内的商店表';

-- ----------------------------
-- Table structure for order_shop_commodity
-- ----------------------------
DROP TABLE IF EXISTS `order_shop_commodity`;
CREATE TABLE `order_shop_commodity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(30) NOT NULL COMMENT '订单编号',
  `shop_id` varchar(30) NOT NULL COMMENT '商店id',
  `commodity_id` varchar(30) NOT NULL COMMENT '商品id',
  `specifications_id` varchar(30) NOT NULL COMMENT '规格id',
  `buy` int(11) NOT NULL COMMENT '购买数量',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `real_price` decimal(10,2) NOT NULL COMMENT '实际价格',
  `free_price` decimal(10,2) NOT NULL COMMENT '优惠价格',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`order_id`,`shop_id`,`commodity_id`,`specifications_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商店内商品表';

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(30) NOT NULL COMMENT '商店id',
  `shop_name` varchar(30) NOT NULL COMMENT '商店名称',
  `user_id` varchar(30) NOT NULL COMMENT '店主id',
  `category_id` varchar(30) NOT NULL COMMENT '分类',
  `start` int(11) DEFAULT NULL COMMENT '商店星级',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标记，1为删除，其它为存在',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`shop_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商店表';

-- ----------------------------
-- Table structure for shop_category
-- ----------------------------
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `parent_category_id` int(11) DEFAULT NULL COMMENT '分类父id',
  `category_name` varchar(30) NOT NULL COMMENT '分类名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标记，1为删除，其它为存在',
  `enable` int(11) DEFAULT NULL COMMENT '启用标志，1为停用，其它为启用',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商店分类表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `nikename` varchar(30) NOT NULL COMMENT '昵称',
  `is_shopkeeper` int(11) DEFAULT NULL COMMENT '是否店主，1为店主，其它非店主',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标记，1删除，其它存在',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
