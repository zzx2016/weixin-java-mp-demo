/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50024
Source Host           : localhost:3306
Source Database       : customboot

Target Server Type    : MYSQL
Target Server Version : 50024
File Encoding         : 65001

Date: 2017-01-12 13:58:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `invitation_info`
-- ----------------------------
DROP TABLE IF EXISTS `invitation_info`;
CREATE TABLE `invitation_info` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键id',
  `user_code` varchar(64) default NULL COMMENT '用户微信code，唯一标识',
  `invite_type` tinyint(4) default NULL COMMENT '请帖类型',
  `men_name` varchar(255) default NULL COMMENT '男方姓名',
  `feamle_name` varchar(255) default NULL COMMENT '女方姓名',
  `address` varchar(255) default NULL COMMENT '典礼地址',
  `telephone` int(20) default NULL COMMENT '手机号',
  `wedding_time` date default NULL COMMENT '典礼时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invitation_info
-- ----------------------------

-- ----------------------------
-- Table structure for `img_info`
-- ----------------------------
DROP TABLE IF EXISTS `img_info`;
CREATE TABLE `img_info` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键id',
  `original_name` varchar(255) default NULL COMMENT '图片原始名字',
  `img_name` varchar(255) default NULL COMMENT '服务器上图片名字',
  `path` varchar(255) default NULL COMMENT '图片路径',
  `invite_id` int(11) default NULL COMMENT '请帖id，invitation_info的外键',
  `create_time` datetime default NULL COMMENT '添加时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of img_info
-- ----------------------------