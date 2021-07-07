/*
Navicat MySQL Data Transfer

Source Server         : mysql56
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : db_0

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2021-07-07 08:42:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for freeze
-- ----------------------------
DROP TABLE IF EXISTS `freeze`;
CREATE TABLE `freeze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `transfer_id` varchar(36) NOT NULL COMMENT '交易id',
  `money` float NOT NULL COMMENT '冻结金额',
  `type` varchar(20) NOT NULL COMMENT '币种',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`user_id`,`transfer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for transfer
-- ----------------------------
DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transfer_id` varchar(36) NOT NULL COMMENT '交易id，使用uuid',
  `user_id_from` varchar(20) NOT NULL COMMENT '交易发起方id',
  `user_id_to` varchar(20) NOT NULL COMMENT '交易对手方id',
  `money_from` float NOT NULL COMMENT '交易发起方金额',
  `money_to` float NOT NULL COMMENT '交易对手方金额',
  `type_from` varchar(20) NOT NULL COMMENT '交易发起方币种',
  `type_to` varchar(20) NOT NULL COMMENT '交易对手方币种',
  `status` varchar(20) NOT NULL COMMENT '交易状态',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `transfer_id` (`transfer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `usd` float NOT NULL COMMENT '美元余额',
  `cny` float NOT NULL COMMENT '人名币余额',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
