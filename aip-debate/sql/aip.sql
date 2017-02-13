/*
Navicat MySQL Data Transfer

Source Server         : haoyi3
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : aip

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-06-01 08:29:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `debate`
-- ----------------------------
DROP TABLE IF EXISTS `debate`;
CREATE TABLE `debate` (
  `ID` varchar(64) NOT NULL,
  `TITLE` varchar(128) NOT NULL,
  `STATE` varchar(16) DEFAULT NULL,
  `SUPPLEMENT_EXPLANATION` text,
  `CREATOR` varchar(64) DEFAULT NULL,
  `CREATE_TIME` bigint(20) DEFAULT NULL,
  `UPDATEDBY` varchar(64) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `VERSION` smallint(6) DEFAULT NULL,
  `IS_DELETED` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of debate
-- ----------------------------

-- ----------------------------
-- Table structure for `debate_argument`
-- ----------------------------
DROP TABLE IF EXISTS `debate_argument`;
CREATE TABLE `debate_argument` (
  `ID` varchar(64) NOT NULL,
  `DESCRIPTION` varchar(4000) NOT NULL,
  `SUPPLEMENT_EXPLANATION` text,
  `ORDER_NO` smallint(6) DEFAULT NULL,
  `DABATE_ID` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of debate_argument
-- ----------------------------

-- ----------------------------
-- Table structure for `debate_argument_stat`
-- ----------------------------
DROP TABLE IF EXISTS `debate_argument_stat`;
CREATE TABLE `debate_argument_stat` (
  `ID` varchar(64) NOT NULL,
  `ARGUMENT_ID` varchar(64) NOT NULL,
  `DEBATE_RELATION_ID` varchar(64) DEFAULT NULL,
  `BEST_VIEWS_ID` varchar(64) DEFAULT NULL,
  `VIEWS_NUM` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of debate_argument_stat
-- ----------------------------

-- ----------------------------
-- Table structure for `debate_relation`
-- ----------------------------
DROP TABLE IF EXISTS `debate_relation`;
CREATE TABLE `debate_relation` (
  `ID` varchar(64) NOT NULL DEFAULT '',
  `RELATION_ID` varchar(64) DEFAULT NULL,
  `DEBATE_ID` varchar(64) DEFAULT NULL,
  `START_TIME` bigint(20) DEFAULT NULL,
  `END_TIME` bigint(20) DEFAULT NULL,
  `PARTICIPATE_NUM` smallint(6) DEFAULT NULL,
  `BROWSE_NUM` smallint(6) DEFAULT NULL,
  `FOLLOW_NUM` smallint(6) DEFAULT NULL,
  `COLLECT_NUM` smallint(6) DEFAULT NULL,
  `LAST_PUBLISH_VIEWS_TIME` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of debate_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `debate_user`
-- ----------------------------
DROP TABLE IF EXISTS `debate_user`;
CREATE TABLE `debate_user` (
  `ID` varchar(64) NOT NULL DEFAULT '',
  `DEBATE_RELATION_ID` varchar(64) DEFAULT NULL,
  `ARGUMENT_ID` varchar(64) DEFAULT NULL,
  `VIEWS_NUM` smallint(6) DEFAULT NULL,
  `COMMENTS_NUM` smallint(6) DEFAULT NULL,
  `CREATOR` varchar(64) DEFAULT NULL,
  `CREATE_TIME` bigint(20) DEFAULT NULL,
  `UPDATEDBY` varchar(64) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `VERSION` smallint(6) DEFAULT NULL,
  `IS_DELETED` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of debate_user
-- ----------------------------

-- ----------------------------
-- Table structure for `debate_user_views`
-- ----------------------------
DROP TABLE IF EXISTS `debate_user_views`;
CREATE TABLE `debate_user_views` (
  `ID` varchar(64) NOT NULL,
  `DEBATE_USER_ID` varchar(64) NOT NULL,
  `VIEWS_CONTENT` text NOT NULL,
  `IS_BEST_VIEWS` char(1) DEFAULT NULL,
  `SUPPORT_NUM` smallint(6) DEFAULT NULL,
  `COMMENTS_NUM` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of debate_user_views
-- ----------------------------
