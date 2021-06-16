/*
Navicat MySQL Data Transfer

Source Server         : mysql56
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : baotao

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2021-06-10 23:25:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user2
-- ----------------------------
DROP TABLE IF EXISTS `user2`;
CREATE TABLE `user2` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user2
-- ----------------------------
INSERT INTO `user2` VALUES ('1', 'tony', '18');
INSERT INTO `user2` VALUES ('2', 'jimy', '14');
