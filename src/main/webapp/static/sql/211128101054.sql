/*
MySQL Backup
Source Server Version: 5.7.36
Source Database: test
Date: 2021/11/28 10:10:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `administrator_info`
-- ----------------------------
DROP TABLE IF EXISTS `administrator_info`;
CREATE TABLE `administrator_info` (
  `user_name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `operator` varchar(30) NOT NULL,
  `operator_phone` varchar(30) NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `operator` (`operator`),
  UNIQUE KEY `operator_phone` (`operator_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `approval`
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
  PRIMARY KEY (`serialnumber`),
  KEY `approval_index` (`person_id`,`leave_type`),
  KEY `approval_leave_type` (`leave_type`),
  CONSTRAINT `approval_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `approval_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `approval_backups`
-- ----------------------------
DROP TABLE IF EXISTS `approval_backups`;
CREATE TABLE `approval_backups` (
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
  `approval_status` varchar(10) NOT NULL,
  PRIMARY KEY (`serialnumber`),
  KEY `approval_backups_index` (`person_id`,`leave_type`),
  KEY `approval_backups_leave_type` (`leave_type`),
  CONSTRAINT `approval_backups_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `approval_backups_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `history_info`
-- ----------------------------
DROP TABLE IF EXISTS `history_info`;
CREATE TABLE `history_info` (
  `leave_serialnumber` int(11) unsigned zerofill NOT NULL,
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
  PRIMARY KEY (`leave_serialnumber`),
  KEY `history_info_index` (`leave_serialnumber`,`person_id`,`leave_type`),
  KEY `history_info_person_id` (`person_id`),
  KEY `history_info_leave_type` (`leave_type`),
  CONSTRAINT `history_info_leave_serialnumber` FOREIGN KEY (`leave_serialnumber`) REFERENCES `approval` (`serialnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_info_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `history_info_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `history_info_backups`
-- ----------------------------
DROP TABLE IF EXISTS `history_info_backups`;
CREATE TABLE `history_info_backups` (
  `leave_serialnumber` int(11) unsigned zerofill NOT NULL,
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
  `remark` varchar(3000) DEFAULT NULL,
  `end_date_maybe` date NOT NULL,
  `start_leave_operator` varchar(30) NOT NULL,
  `end_date` date NOT NULL,
  `end_leave_remark` varchar(3000) DEFAULT NULL,
  `end_leave_operator` varchar(30) NOT NULL,
  `leave_days_actual` tinyint(4) unsigned NOT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_operator` varchar(30) DEFAULT NULL,
  `delete_reason` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`leave_serialnumber`),
  KEY `history_infobackups_index` (`leave_serialnumber`,`person_id`,`leave_type`),
  KEY `history_info_backups_personid` (`person_id`),
  KEY `history_info_backups_leavetype` (`leave_type`),
  CONSTRAINT `history_info_backups_leave_serialnumber` FOREIGN KEY (`leave_serialnumber`) REFERENCES `approval` (`serialnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_info_backups_leavetype` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `history_info_backups_personid` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `leader_subordinate`
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
--  Table structure for `leave_type`
-- ----------------------------
DROP TABLE IF EXISTS `leave_type`;
CREATE TABLE `leave_type` (
  `id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `leave_type` varchar(20) NOT NULL,
  PRIMARY KEY (`leave_type`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `level_info`
-- ----------------------------
DROP TABLE IF EXISTS `level_info`;
CREATE TABLE `level_info` (
  `id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `level_name` varchar(100) NOT NULL,
  PRIMARY KEY (`level_name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `nation_info`
-- ----------------------------
DROP TABLE IF EXISTS `nation_info`;
CREATE TABLE `nation_info` (
  `id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `nation_name` varchar(20) NOT NULL,
  PRIMARY KEY (`nation_name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `office_info`
-- ----------------------------
DROP TABLE IF EXISTS `office_info`;
CREATE TABLE `office_info` (
  `id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `office_name` varchar(300) NOT NULL,
  PRIMARY KEY (`office_name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `person_info`
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `resume_work`
-- ----------------------------
DROP TABLE IF EXISTS `resume_work`;
CREATE TABLE `resume_work` (
  `leave_serialnumber` int(11) unsigned zerofill NOT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` int(8) unsigned DEFAULT NULL,
  `leave_type` varchar(20) DEFAULT NULL,
  `start_date` date NOT NULL,
  `leave_days` tinyint(4) unsigned NOT NULL,
  `work_leader` varchar(30) NOT NULL,
  `leave_reason` varchar(2000) NOT NULL,
  `approver` varchar(30) NOT NULL,
  `depart_location` varchar(300) NOT NULL,
  `arrive_location` varchar(300) NOT NULL,
  `start_leave_remark` varchar(3000) DEFAULT NULL,
  `end_date_maybe` date NOT NULL,
  `start_leave_operator` varchar(30) NOT NULL,
  PRIMARY KEY (`leave_serialnumber`),
  KEY `resume_work_index` (`leave_serialnumber`,`person_id`,`leave_type`),
  KEY `resume_work_person_id` (`person_id`),
  KEY `resume_work_leave_type` (`leave_type`),
  CONSTRAINT `resume_work_leave_serialnumber` FOREIGN KEY (`leave_serialnumber`) REFERENCES `approval` (`serialnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resume_work_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `resume_work_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `system_info`
-- ----------------------------
DROP TABLE IF EXISTS `system_info`;
CREATE TABLE `system_info` (
  `sms_alert` tinyint(2) NOT NULL DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `operator` varchar(30) NOT NULL,
  `operator_phone` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `operator` (`operator`),
  UNIQUE KEY `operator_phone` (`operator_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `approval` VALUES ('00000000001','陈天飞','9','年休假','2021-11-26','50','测试','测试','测试','测试','测试','测试','2022-01-15','测试请假操作者','待审批'), ('00000000002','陈天飞','9','调休','2021-11-27','20','测试','测试','测试','测试','测试','测试','2021-12-17','测试请假操作者','待审批'), ('00000000003','陈天飞','9','调休','2021-11-27','20','测试','测试','测试','测试','测试','测试','2021-12-17','测试请假操作者','待审批'), ('00000000004','陈天飞','9','病假','2021-11-27','20','测试','测试','测试','测试','测试','测试','2021-12-17','测试请假操作者','待审批'), ('00000000005','陈天飞','9','调休','2021-11-27','0','测试','测试','测试','测试','测试','测试','2021-11-27','测试请假操作者','待审批'), ('00000000006','测试2','2','培训','2021-11-27','50','测试','测试','测试','测试','测试','测试','2022-01-16','测试请假操作者','待审批'), ('00000000007','测试3','3','培训','2021-11-27','50','测试','测试','测试','测试','测试','测试','2022-01-16','测试请假操作者','待审批'), ('00000000008','测试3','3','培训','2021-11-27','50','测试','测试','测试','测试','测试','测试','2022-01-16','测试请假操作者','待审批'), ('00000000009','测试4','4','轮休','2021-11-27','50','测试','测试','测试','测试','测试','测试','2022-01-16','测试请假操作者','待审批');
INSERT INTO `leave_type` VALUES ('00000001','年休假'), ('00000002','调休'), ('00000003','轮休'), ('00000004','病假'), ('00000005','培训'), ('00000006','陪产假'), ('00000007','产假');
INSERT INTO `level_info` VALUES ('00000001','二级科员'), ('00000002','一级科员'), ('00000003','四级主任科员'), ('00000004','三级主任科员'), ('00000005','二级主任科员'), ('00000006','一级主任科员');
INSERT INTO `nation_info` VALUES ('00000001','汉族'), ('00000002','蒙古族'), ('00000003','回族'), ('00000004','藏族'), ('00000005','维吾尔族'), ('00000006','苗族'), ('00000007','彝族'), ('00000008','壮族'), ('00000009','布依族'), ('00000010','朝鲜族'), ('00000011','满族'), ('00000012','侗族'), ('00000013','瑶族'), ('00000014','白族'), ('00000015','土家族'), ('00000016','哈尼族'), ('00000017','哈萨克族'), ('00000018','傣族'), ('00000019','黎族'), ('00000020','僳僳族'), ('00000021','佤族'), ('00000022','畲族'), ('00000023','高山族'), ('00000024','拉祜族'), ('00000025','水族'), ('00000026','东乡族'), ('00000027','纳西族'), ('00000028','景颇族'), ('00000029','柯尔克孜族'), ('00000030','土族'), ('00000031','达斡尔族'), ('00000032','仫佬族'), ('00000033','羌族'), ('00000034','布朗族'), ('00000035','撒拉族'), ('00000036','毛南族'), ('00000037','仡佬族'), ('00000038','锡伯族'), ('00000039','阿昌族'), ('00000040','普米族'), ('00000041','塔吉克族'), ('00000042','怒族'), ('00000043','乌孜别克族'), ('00000044','俄罗斯族'), ('00000045','鄂温克族'), ('00000046','德昂族'), ('00000047','保安族'), ('00000048','裕固族'), ('00000049','京族'), ('00000050','塔塔尔族'), ('00000051','独龙族'), ('00000052','鄂伦春族'), ('00000053','赫哲族'), ('00000054','门巴族'), ('00000055','珞巴族'), ('00000056','基诺族');
INSERT INTO `office_info` VALUES ('00000001','仁钦则乡人民政府'), ('00000002','县委办'), ('00000003','政府办'), ('00000004','县委组织部'), ('00000005','县委政法委'), ('00000006','县纪委');
INSERT INTO `person_info` VALUES ('00000001','测试1','汉族','男','1994-05-17','四川省成都市青羊区','仁钦则乡人民政府','党委副书记、政府乡长','一级主任科员','18089922200','50','二类区'), ('00000002','测试2','藏族','女','1995-06-23','西藏自治区日喀则市谢通门县','县委办','县委办主任','二级主任科员','18289922345','40','四类区'), ('00000003','测试3','壮族','女','1996-07-24','广西壮族自治区桂林市','县委组织部','县委组织部副部长','三级主任科员','13689559706','60','二类区'), ('00000004','测试4','蒙古族','男','1974-05-25','内蒙古自治区呼和浩特市','县委政法委','县委政法委工作人员','四级主任科员','13755803250','70','三类区'), ('00000005','测试5','维吾尔族','男','1986-01-22','新疆维吾尔自治区乌鲁木齐市','县委办','县委办科员','一级科员','18735980966','70','三类区'), ('00000006','测试6','白族','女','1988-09-23','云南省大理白族自治州','政府办','政府办科员','一级科员','13580979985','50','二类区'), ('00000007','测试7','满族','男','1989-09-18','北京市东城区','县委办','县委办工作人员','四级主任科员','15233654985','50','三类区'), ('00000008','测试8','傣族','男','1980-10-01','云南省西双版纳傣族自治州','政府办','政府办主任','一级主任科员','13955895653','50','四类区'), ('00000009','陈天飞','汉族','男','1994-05-17','四川省 眉山市 彭山区','仁钦则乡人民政府','党委书记','一级主任科员','18089922014','60','二类区');
