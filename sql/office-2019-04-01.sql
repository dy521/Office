/*
Navicat MySQL Data Transfer

Source Server         : master
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : office

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-04-01 20:51:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '17695920199', '111');

-- ----------------------------
-- Table structure for `atten`
-- ----------------------------
DROP TABLE IF EXISTS `atten`;
CREATE TABLE `atten` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empid` int(11) DEFAULT NULL,
  `up_time` varchar(50) DEFAULT NULL,
  `down_time` varchar(50) DEFAULT NULL,
  `is_use` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atten
-- ----------------------------
INSERT INTO `atten` VALUES ('1', '1', '2019-03-28', '2019-03-28', '是');
INSERT INTO `atten` VALUES ('2', '6', '2019-03-29', '2019-03-29 ', '是');
INSERT INTO `atten` VALUES ('3', '1', '2019-03-31', '2019-03-31', '否');

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `duty` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('2', '技术部', '做开发');
INSERT INTO `dept` VALUES ('3', '新闻部', '发新闻					 ');
INSERT INTO `dept` VALUES ('4', '管理部', '管理公司职务				 ');
INSERT INTO `dept` VALUES ('5', '人事部', '管理公司人事行政	 ');

-- ----------------------------
-- Table structure for `email`
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email
-- ----------------------------
INSERT INTO `email` VALUES ('1', 'djw745917@163.com');

-- ----------------------------
-- Table structure for `emp`
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('1', '员工1', '普通员工', '3', 'emp1', '111', '员工', '女');
INSERT INTO `emp` VALUES ('2', '员工2', '优秀', '2', 'emp2', '111', '员工', '女');
INSERT INTO `emp` VALUES ('5', '员工3', '优秀', '2', 'emp3', '111', '员工', '男');
INSERT INTO `emp` VALUES ('6', '技术经理', '优秀', '2', 'mag1', '111', '经理', '男');
INSERT INTO `emp` VALUES ('7', '新闻经理', '优秀', '3', 'mag2', '111', '经理', '男');
INSERT INTO `emp` VALUES ('8', '管理经理', '良好', '4', 'mag3', '111', '经理', '女');

-- ----------------------------
-- Table structure for `lend`
-- ----------------------------
DROP TABLE IF EXISTS `lend`;
CREATE TABLE `lend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empid` int(11) DEFAULT NULL,
  `objid` int(11) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lend
-- ----------------------------

-- ----------------------------
-- Table structure for `msg`
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES ('3', '公告1', '今天不加班			 ', '2019-03-27 20:53:25', 'd:/coin/office/msg/65871231b0cd41649a5058d9493be482.jpg');
INSERT INTO `msg` VALUES ('4', '公文2', '无				 ', '2019-03-28 12:17:40', 'd:/coin/office/office/f119770acdde4b90a4b604c84a020ddf.jpg');

-- ----------------------------
-- Table structure for `obj`
-- ----------------------------
DROP TABLE IF EXISTS `obj`;
CREATE TABLE `obj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of obj
-- ----------------------------
INSERT INTO `obj` VALUES ('1', '桌子', '2019-03-28 21:29:15', '未借出');
INSERT INTO `obj` VALUES ('2', '椅子', '2019-03-28 21:29:25', '未借出');
INSERT INTO `obj` VALUES ('5', '水性笔', '2019-03-28 21:30:07', '未借出');

-- ----------------------------
-- Table structure for `office`
-- ----------------------------
DROP TABLE IF EXISTS `office`;
CREATE TABLE `office` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `empid` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL COMMENT '已发布  经理通过  管理员通过',
  `reciver_str` varchar(50) DEFAULT NULL,
  `emp_str` varchar(50) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of office
-- ----------------------------
INSERT INTO `office` VALUES ('1', '123', '1', '2019-03-27 21:46:58', '经理不通过', '2,5', '', 'd:/coin/office/office/1c34660d5d0b4813ae6f656613216787.jpg', '123				 ');
INSERT INTO `office` VALUES ('2', '公文2', '1', '2019-03-28 12:17:40', '管理员通过', '2,5', '', 'd:/coin/office/office/f119770acdde4b90a4b604c84a020ddf.jpg', '无				 ');
