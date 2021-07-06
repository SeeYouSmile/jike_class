/*
Navicat MySQL Data Transfer

Source Server         : mysql56
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : db_0

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2021-07-07 00:05:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for freeze
-- ----------------------------
DROP TABLE IF EXISTS `freeze`;
CREATE TABLE `freeze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `transfer_id` varchar(36) NOT NULL,
  `money` float DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`user_id`,`transfer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for transfer
-- ----------------------------
DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transfer_id` varchar(36) NOT NULL,
  `user_id_from` varchar(20) NOT NULL,
  `user_id_to` varchar(20) NOT NULL,
  `money_from` float NOT NULL,
  `money_to` float NOT NULL,
  `type_from` varchar(20) NOT NULL,
  `type_to` varchar(20) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transfer_id` (`transfer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `usd` float NOT NULL,
  `cny` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
