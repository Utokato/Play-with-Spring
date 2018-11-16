/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : mstore

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-11-16 14:22:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '书籍名称',
  `author` varchar(32) NOT NULL COMMENT '作者',
  `version` varchar(16) NOT NULL COMMENT '版本',
  `pages` int(32) NOT NULL COMMENT '页数',
  `price` decimal(8,2) NOT NULL COMMENT '价格',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书籍表';

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1062244882999873536', '挪威的森林', '村上春树', '典藏版', '495', '56.30', '2018-11-13 15:24:50', '2018-11-13 15:24:50');
INSERT INTO `book` VALUES ('1062246651712679936', '名人传', '罗曼·罗兰', '典藏版', '596', '63.20', '2018-11-13 15:31:52', '2018-11-13 15:31:52');
INSERT INTO `book` VALUES ('1062246866494681088', '活着', '余华', '典藏版', '323', '23.50', '2018-11-13 15:32:43', '2018-11-13 15:32:43');
INSERT INTO `book` VALUES ('1062248971448012800', '拿破仑传', '埃米尔·路德维希', '典藏版', '396', '26.90', '2018-11-13 15:41:05', '2018-11-13 15:41:05');
INSERT INTO `book` VALUES ('1062297651236528128', '笑傲江湖', '金庸', '典藏版', '699', '63.90', '2018-11-13 18:54:31', '2018-11-13 18:54:31');
INSERT INTO `book` VALUES ('1062307864815579136', '天龙八部', '金庸', '典藏版', '563', '63.20', '2018-11-13 19:35:06', '2018-11-13 19:35:06');
INSERT INTO `book` VALUES ('1063044367808167936', '射雕英雄传', '金庸', '少儿版', '323', '23.50', '2018-11-15 20:21:42', '2018-11-15 20:21:42');

-- ----------------------------
-- Table structure for book_detail
-- ----------------------------
DROP TABLE IF EXISTS `book_detail`;
CREATE TABLE `book_detail` (
  `id` varchar(64) NOT NULL,
  `characters` varchar(128) DEFAULT NULL COMMENT '主人公',
  `introduction` varchar(1024) DEFAULT NULL COMMENT '简介',
  `other` varchar(128) DEFAULT NULL COMMENT '其他说明',
  `book_id` varchar(64) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_book_id` (`book_id`) USING BTREE,
  KEY `idx_book_id` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书籍详情表';

-- ----------------------------
-- Records of book_detail
-- ----------------------------
INSERT INTO `book_detail` VALUES ('1062244884920864768', '渡边彻，小林绿子，直子', '爱情故事', '无', '1062244882999873536', '2018-11-13 15:24:50', '2018-11-13 15:24:50');
INSERT INTO `book_detail` VALUES ('1062246653642059776', '贝多芬，托尔斯泰，米开朗琪罗', '励志故事', '无', '1062246651712679936', '2018-11-13 15:31:52', '2018-11-13 15:31:52');
INSERT INTO `book_detail` VALUES ('1062246868340174848', '福贵', '过去的故事', '无', '1062246866494681088', '2018-11-13 15:32:43', '2018-11-13 15:32:43');
INSERT INTO `book_detail` VALUES ('1062248973373198336', '拿破仑', '名人故事', '无', '1062248971448012800', '2018-11-13 15:41:05', '2018-11-13 15:41:05');
INSERT INTO `book_detail` VALUES ('1062297653769887744', '令狐冲，东方不败', '仗剑天涯', '无', '1062297651236528128', '2018-11-13 18:54:31', '2018-11-13 18:54:31');
INSERT INTO `book_detail` VALUES ('1062307868049387520', '乔峰，虚竹，段誉，阿朱', '侠骨柔情', '无', '1062307864815579136', '2018-11-13 19:37:48', '2018-11-13 19:37:42');
INSERT INTO `book_detail` VALUES ('1063044368483450880', '郭靖，黄蓉，杨康，穆念慈', '铁血丹心', '罗文主唱', '1063044367808167936', '2018-11-15 20:21:42', '2018-11-15 20:21:42');
SET FOREIGN_KEY_CHECKS=1;
