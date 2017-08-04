/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : zhwy

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-02 22:03:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `aos_api_res`
-- ----------------------------
DROP TABLE IF EXISTS `aos_api_res`;
CREATE TABLE `aos_api_res` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `pid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apiname` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'api名称',
  `apiurl` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'API请求路径',
  `available` int(11) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='api资源表';

-- ----------------------------
-- Records of aos_api_res
-- ----------------------------
INSERT INTO `aos_api_res` VALUES ('3', '0', 'api', '/api/', '1', '2017-04-28 10:07:29');

-- ----------------------------
-- Table structure for `aos_api_user`
-- ----------------------------
DROP TABLE IF EXISTS `aos_api_user`;
CREATE TABLE `aos_api_user` (
  `userid` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `account` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账户',
  `password` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '秘钥',
  `usertype` int(11) DEFAULT NULL COMMENT '用户类型',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `available` int(11) DEFAULT NULL COMMENT '是否有效  1:有效  0:无效   默认1',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `account_key` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='API用户表';

-- ----------------------------
-- Records of aos_api_user
-- ----------------------------
INSERT INTO `aos_api_user` VALUES ('2', 'node', 'node', 'ts123456', '0', '2017-04-28 10:08:18', '1');

-- ----------------------------
-- Table structure for `aos_api_user_res`
-- ----------------------------
DROP TABLE IF EXISTS `aos_api_user_res`;
CREATE TABLE `aos_api_user_res` (
  `apiuserid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apiresid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户api授权表';

-- ----------------------------
-- Records of aos_api_user_res
-- ----------------------------
INSERT INTO `aos_api_user_res` VALUES ('2', '3');
