/*
Navicat MySQL Data Transfer

Source Server         : CTFConnect
Source Server Version : 50736
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2021-12-12 12:19:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for approval
-- ----------------------------
DROP TABLE IF EXISTS `approval`;
CREATE TABLE `approval` (
  `serialnumber` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `person_id` int(8) unsigned DEFAULT NULL,
  `leave_type` varchar(20) DEFAULT NULL,
  `start_date` date NOT NULL,
  `leave_days_projected` tinyint(4) unsigned NOT NULL,
  `work_leader` varchar(30) NOT NULL,
  `leave_reason` varchar(2000) NOT NULL,
  `approver` varchar(30) NOT NULL,
  `depart_location` varchar(300) NOT NULL,
  `arrive_location` varchar(300) NOT NULL,
  `start_leave_remark` varchar(3000) DEFAULT NULL,
  `end_date_maybe` date NOT NULL,
  `start_leave_operator` varchar(30) NOT NULL,
  `approval_status` varchar(10) NOT NULL,
  `approval_reason` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`serialnumber`),
  KEY `approval_index` (`person_id`,`leave_type`),
  KEY `approval_leave_type` (`leave_type`),
  CONSTRAINT `approval_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `approval_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of approval
-- ----------------------------

-- ----------------------------
-- Table structure for history_info
-- ----------------------------
DROP TABLE IF EXISTS `history_info`;
CREATE TABLE `history_info` (
  `serialnumber` int(11) unsigned zerofill NOT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` int(8) unsigned DEFAULT NULL,
  `leave_type` varchar(20) DEFAULT NULL,
  `start_date` date NOT NULL,
  `leave_days_projected` tinyint(4) unsigned NOT NULL,
  `work_leader` varchar(30) NOT NULL,
  `leave_reason` varchar(2000) NOT NULL,
  `approver` varchar(30) NOT NULL,
  `depart_location` varchar(300) NOT NULL,
  `arrive_location` varchar(300) NOT NULL,
  `start_leave_remark` varchar(3000) DEFAULT NULL,
  `end_date_maybe` date NOT NULL,
  `start_leave_operator` varchar(30) NOT NULL,
  `end_date` date NOT NULL,
  `end_leave_remark` varchar(3000) DEFAULT NULL,
  `end_leave_operator` varchar(30) NOT NULL,
  `leave_days_actual` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`serialnumber`),
  KEY `history_info_index` (`serialnumber`,`person_id`,`leave_type`),
  KEY `history_info_person_id` (`person_id`),
  KEY `history_info_leave_type` (`leave_type`),
  CONSTRAINT `history_info_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `history_info_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_info_serialnumber` FOREIGN KEY (`serialnumber`) REFERENCES `approval` (`serialnumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history_info
-- ----------------------------

-- ----------------------------
-- Table structure for history_info_backups
-- ----------------------------
DROP TABLE IF EXISTS `history_info_backups`;
CREATE TABLE `history_info_backups` (
  `serialnumber` int(11) unsigned zerofill NOT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` int(8) unsigned DEFAULT NULL,
  `leave_type` varchar(20) DEFAULT NULL,
  `start_date` date NOT NULL,
  `leave_days_projected` tinyint(4) unsigned NOT NULL,
  `work_leader` varchar(30) NOT NULL,
  `leave_reason` varchar(2000) NOT NULL,
  `approver` varchar(30) NOT NULL,
  `depart_location` varchar(300) NOT NULL,
  `arrive_location` varchar(300) NOT NULL,
  `start_leave_remark` varchar(3000) DEFAULT NULL,
  `end_date_maybe` date NOT NULL,
  `start_leave_operator` varchar(30) NOT NULL,
  `end_date` date NOT NULL,
  `end_leave_remark` varchar(3000) DEFAULT NULL,
  `end_leave_operator` varchar(30) NOT NULL,
  `leave_days_actual` tinyint(4) unsigned NOT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_operator` varchar(30) DEFAULT NULL,
  `delete_reason` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`serialnumber`),
  KEY `history_infobackups_index` (`serialnumber`,`person_id`,`leave_type`),
  KEY `history_info_backups_personid` (`person_id`),
  KEY `history_info_backups_leavetype` (`leave_type`),
  CONSTRAINT `history_info_backups_leavetype` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `history_info_backups_personid` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_info_backups_serialnumber` FOREIGN KEY (`serialnumber`) REFERENCES `approval` (`serialnumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history_info_backups
-- ----------------------------

-- ----------------------------
-- Table structure for leader_subordinate
-- ----------------------------
DROP TABLE IF EXISTS `leader_subordinate`;
CREATE TABLE `leader_subordinate` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `leader_id` int(8) unsigned DEFAULT NULL,
  `subordinate_id` int(8) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `leader_subordinate_leader_id` (`leader_id`),
  KEY `leader_subordinate_subordinate_id` (`subordinate_id`),
  CONSTRAINT `leader_subordinate_leader_id` FOREIGN KEY (`leader_id`) REFERENCES `person_info` (`person_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `leader_subordinate_subordinate_id` FOREIGN KEY (`subordinate_id`) REFERENCES `person_info` (`person_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leader_subordinate
-- ----------------------------

-- ----------------------------
-- Table structure for leave_type
-- ----------------------------
DROP TABLE IF EXISTS `leave_type`;
CREATE TABLE `leave_type` (
  `id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `leave_type` varchar(20) NOT NULL,
  PRIMARY KEY (`leave_type`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_type
-- ----------------------------
INSERT INTO `leave_type` VALUES ('00000001', '调休');

-- ----------------------------
-- Table structure for level_info
-- ----------------------------
DROP TABLE IF EXISTS `level_info`;
CREATE TABLE `level_info` (
  `id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `level_name` varchar(100) NOT NULL,
  PRIMARY KEY (`level_name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of level_info
-- ----------------------------

-- ----------------------------
-- Table structure for nation_info
-- ----------------------------
DROP TABLE IF EXISTS `nation_info`;
CREATE TABLE `nation_info` (
  `id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `nation_name` varchar(20) NOT NULL,
  PRIMARY KEY (`nation_name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nation_info
-- ----------------------------
INSERT INTO `nation_info` VALUES ('00000001', '汉族');
INSERT INTO `nation_info` VALUES ('00000002', '蒙古族');
INSERT INTO `nation_info` VALUES ('00000003', '回族');
INSERT INTO `nation_info` VALUES ('00000004', '藏族');
INSERT INTO `nation_info` VALUES ('00000005', '维吾尔族');
INSERT INTO `nation_info` VALUES ('00000006', '苗族');
INSERT INTO `nation_info` VALUES ('00000007', '彝族');
INSERT INTO `nation_info` VALUES ('00000008', '壮族');
INSERT INTO `nation_info` VALUES ('00000009', '布依族');
INSERT INTO `nation_info` VALUES ('00000010', '朝鲜族');
INSERT INTO `nation_info` VALUES ('00000011', '满族');
INSERT INTO `nation_info` VALUES ('00000012', '侗族');
INSERT INTO `nation_info` VALUES ('00000013', '瑶族');
INSERT INTO `nation_info` VALUES ('00000014', '白族');
INSERT INTO `nation_info` VALUES ('00000015', '土家族');
INSERT INTO `nation_info` VALUES ('00000016', '哈尼族');
INSERT INTO `nation_info` VALUES ('00000017', '哈萨克族');
INSERT INTO `nation_info` VALUES ('00000018', '傣族');
INSERT INTO `nation_info` VALUES ('00000019', '黎族');
INSERT INTO `nation_info` VALUES ('00000020', '僳僳族');
INSERT INTO `nation_info` VALUES ('00000021', '佤族');
INSERT INTO `nation_info` VALUES ('00000022', '畲族');
INSERT INTO `nation_info` VALUES ('00000023', '高山族');
INSERT INTO `nation_info` VALUES ('00000024', '拉祜族');
INSERT INTO `nation_info` VALUES ('00000025', '水族');
INSERT INTO `nation_info` VALUES ('00000026', '东乡族');
INSERT INTO `nation_info` VALUES ('00000027', '纳西族');
INSERT INTO `nation_info` VALUES ('00000028', '景颇族');
INSERT INTO `nation_info` VALUES ('00000029', '柯尔克孜族');
INSERT INTO `nation_info` VALUES ('00000030', '土族');
INSERT INTO `nation_info` VALUES ('00000031', '达斡尔族');
INSERT INTO `nation_info` VALUES ('00000032', '仫佬族');
INSERT INTO `nation_info` VALUES ('00000033', '羌族');
INSERT INTO `nation_info` VALUES ('00000034', '布朗族');
INSERT INTO `nation_info` VALUES ('00000035', '撒拉族');
INSERT INTO `nation_info` VALUES ('00000036', '毛南族');
INSERT INTO `nation_info` VALUES ('00000037', '仡佬族');
INSERT INTO `nation_info` VALUES ('00000038', '锡伯族');
INSERT INTO `nation_info` VALUES ('00000039', '阿昌族');
INSERT INTO `nation_info` VALUES ('00000040', '普米族');
INSERT INTO `nation_info` VALUES ('00000041', '塔吉克族');
INSERT INTO `nation_info` VALUES ('00000042', '怒族');
INSERT INTO `nation_info` VALUES ('00000043', '乌孜别克族');
INSERT INTO `nation_info` VALUES ('00000044', '俄罗斯族');
INSERT INTO `nation_info` VALUES ('00000045', '鄂温克族');
INSERT INTO `nation_info` VALUES ('00000046', '德昂族');
INSERT INTO `nation_info` VALUES ('00000047', '保安族');
INSERT INTO `nation_info` VALUES ('00000048', '裕固族');
INSERT INTO `nation_info` VALUES ('00000049', '京族');
INSERT INTO `nation_info` VALUES ('00000050', '塔塔尔族');
INSERT INTO `nation_info` VALUES ('00000051', '独龙族');
INSERT INTO `nation_info` VALUES ('00000052', '鄂伦春族');
INSERT INTO `nation_info` VALUES ('00000053', '赫哲族');
INSERT INTO `nation_info` VALUES ('00000054', '门巴族');
INSERT INTO `nation_info` VALUES ('00000055', '珞巴族');
INSERT INTO `nation_info` VALUES ('00000056', '基诺族');

-- ----------------------------
-- Table structure for office_info
-- ----------------------------
DROP TABLE IF EXISTS `office_info`;
CREATE TABLE `office_info` (
  `id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `office_name` varchar(300) NOT NULL,
  PRIMARY KEY (`office_name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of office_info
-- ----------------------------
INSERT INTO `office_info` VALUES ('00000001', '超级管理员');
INSERT INTO `office_info` VALUES ('00000002', '县委组织部');

-- ----------------------------
-- Table structure for person_info
-- ----------------------------
DROP TABLE IF EXISTS `person_info`;
CREATE TABLE `person_info` (
  `person_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `nation` varchar(20) NOT NULL,
  `sex` enum('男','女') NOT NULL,
  `birthdate` date NOT NULL,
  `nativeplace` varchar(300) NOT NULL,
  `office` varchar(300) DEFAULT NULL,
  `post` varchar(300) NOT NULL,
  `level` varchar(100) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `allow_leave_days` tinyint(4) unsigned NOT NULL,
  `area_class` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `person_info_office` (`office`),
  KEY `person_info_level` (`level`),
  CONSTRAINT `person_info_level` FOREIGN KEY (`level`) REFERENCES `level_info` (`level_name`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `person_info_office` FOREIGN KEY (`office`) REFERENCES `office_info` (`office_name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person_info
-- ----------------------------

-- ----------------------------
-- Table structure for resume_work
-- ----------------------------
DROP TABLE IF EXISTS `resume_work`;
CREATE TABLE `resume_work` (
  `serialnumber` int(11) unsigned zerofill NOT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` int(8) unsigned DEFAULT NULL,
  `leave_type` varchar(20) DEFAULT NULL,
  `start_date` date NOT NULL,
  `leave_days_projected` tinyint(4) unsigned NOT NULL,
  `work_leader` varchar(30) NOT NULL,
  `leave_reason` varchar(2000) NOT NULL,
  `approver` varchar(30) NOT NULL,
  `depart_location` varchar(300) NOT NULL,
  `arrive_location` varchar(300) NOT NULL,
  `start_leave_remark` varchar(3000) DEFAULT NULL,
  `end_date_maybe` date NOT NULL,
  `start_leave_operator` varchar(30) NOT NULL,
  PRIMARY KEY (`serialnumber`),
  KEY `resume_work_index` (`serialnumber`,`person_id`,`leave_type`),
  KEY `resume_work_person_id` (`person_id`),
  KEY `resume_work_leave_type` (`leave_type`),
  CONSTRAINT `resume_work_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `resume_work_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resume_work_serialnumber` FOREIGN KEY (`serialnumber`) REFERENCES `approval` (`serialnumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resume_work
-- ----------------------------

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) NOT NULL,
  `role_description` varchar(3000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES ('00000000001', '超级管理员', '超级管理员的描述');
INSERT INTO `role_info` VALUES ('00000000002', '组织部', '组织部的描述');
INSERT INTO `role_info` VALUES ('00000000003', '普通用户', '普通用户的描述');

-- ----------------------------
-- Table structure for sendmsglog
-- ----------------------------
DROP TABLE IF EXISTS `sendmsglog`;
CREATE TABLE `sendmsglog` (
  `SerialNo` varchar(100) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Fee` varchar(10) DEFAULT NULL,
  `SessionContext` varchar(100) DEFAULT NULL,
  `Code` varchar(50) DEFAULT NULL,
  `Message` varchar(100) DEFAULT NULL,
  `IsoCode` varchar(50) DEFAULT NULL,
  `RequestId` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sendmsglog
-- ----------------------------

-- ----------------------------
-- Table structure for system_info
-- ----------------------------
DROP TABLE IF EXISTS `system_info`;
CREATE TABLE `system_info` (
  `sms_alert` tinyint(2) NOT NULL DEFAULT '3',
  `doesSendLeader` tinyint(2) NOT NULL DEFAULT '1',
  `doesSendSelf` tinyint(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of system_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `operator` varchar(30) NOT NULL,
  `operator_phone` varchar(30) NOT NULL,
  `office` varchar(300) DEFAULT NULL,
  `role_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_info_index` (`id`,`office`,`role_id`),
  KEY `user_info_office` (`office`),
  KEY `user_info_role_id` (`role_id`),
  CONSTRAINT `user_info_office` FOREIGN KEY (`office`) REFERENCES `office_info` (`office_name`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `user_info_role_id` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('00000000001', 'admin', 'admin123456', 'admin', '00000000000', '超级管理员', '1');
