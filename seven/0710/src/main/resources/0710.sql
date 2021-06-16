/*
Navicat MySQL Data Transfer

Source Server         : mysql56
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : db0

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2021-06-15 22:15:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_0
-- ----------------------------
DROP TABLE IF EXISTS `t_user_0`;
CREATE TABLE `t_user_0` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_0
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_1
-- ----------------------------
DROP TABLE IF EXISTS `t_user_1`;
CREATE TABLE `t_user_1` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_1
-- ----------------------------
