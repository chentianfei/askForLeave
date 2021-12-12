/*
MySQL Backup
Source Server Version: 5.7.36
Source Database: test
Date: 2021/11/15 12:18:23
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `approval`
-- ----------------------------
DROP TABLE IF EXISTS `approval`;
CREATE TABLE `approval` (
  `serialnumber` varchar(100) NOT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` varchar(30) DEFAULT NULL,
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
  `operator` varchar(30) NOT NULL,
  PRIMARY KEY (`serialnumber`),
  UNIQUE KEY `serialnumber` (`serialnumber`),
  KEY `approval_person_id` (`person_id`),
  KEY `approval_leave_type` (`leave_type`),
  CONSTRAINT `approval_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `approval_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `approval_backups`
-- ----------------------------
DROP TABLE IF EXISTS `approval_backups`;
CREATE TABLE `approval_backups` (
  `serialnumber` varchar(100) NOT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` varchar(30) DEFAULT NULL,
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
  `operator` varchar(30) NOT NULL,
  `approval_status` varchar(10) NOT NULL,
  PRIMARY KEY (`serialnumber`),
  UNIQUE KEY `serialnumber` (`serialnumber`),
  KEY `approval_backups_person_id` (`person_id`),
  KEY `approval_backups_leave_type` (`leave_type`),
  CONSTRAINT `approval_backups_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `approval_backups_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `history_info`
-- ----------------------------
DROP TABLE IF EXISTS `history_info`;
CREATE TABLE `history_info` (
  `leave_serialnumber` varchar(100) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` varchar(30) DEFAULT NULL,
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
  KEY `history_info_leave_serialnumber` (`leave_serialnumber`),
  KEY `history_info_person_id` (`person_id`),
  KEY `history_info_leave_type` (`leave_type`),
  CONSTRAINT `history_info_leave_serialnumber` FOREIGN KEY (`leave_serialnumber`) REFERENCES `approval_backups` (`serialnumber`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `history_info_leave_type` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `history_info_person_id` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `history_info_backups`
-- ----------------------------
DROP TABLE IF EXISTS `history_info_backups`;
CREATE TABLE `history_info_backups` (
  `leave_serialnumber` varchar(100) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` varchar(30) DEFAULT NULL,
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
  KEY `history_info_backups_leave_serialnumber` (`leave_serialnumber`),
  KEY `history_info_backups_personid` (`person_id`),
  KEY `history_info_backups_leavetype` (`leave_type`),
  CONSTRAINT `history_info_backups_leave_serialnumber` FOREIGN KEY (`leave_serialnumber`) REFERENCES `approval_backups` (`serialnumber`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `history_info_backups_leavetype` FOREIGN KEY (`leave_type`) REFERENCES `leave_type` (`leave_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `history_info_backups_personid` FOREIGN KEY (`person_id`) REFERENCES `person_info` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `leader_subordinate`
-- ----------------------------
DROP TABLE IF EXISTS `leader_subordinate`;
CREATE TABLE `leader_subordinate` (
  `leader_id` varchar(30) DEFAULT NULL,
  `subordinate_id` varchar(30) DEFAULT NULL,
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
  `leave_type` varchar(20) NOT NULL,
  PRIMARY KEY (`leave_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `level_info`
-- ----------------------------
DROP TABLE IF EXISTS `level_info`;
CREATE TABLE `level_info` (
  `level_name` varchar(100) NOT NULL,
  PRIMARY KEY (`level_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `nation_info`
-- ----------------------------
DROP TABLE IF EXISTS `nation_info`;
CREATE TABLE `nation_info` (
  `nation_name` varchar(20) NOT NULL,
  PRIMARY KEY (`nation_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `office_info`
-- ----------------------------
DROP TABLE IF EXISTS `office_info`;
CREATE TABLE `office_info` (
  `office_name` varchar(300) NOT NULL,
  PRIMARY KEY (`office_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `person_info`
-- ----------------------------
DROP TABLE IF EXISTS `person_info`;
CREATE TABLE `person_info` (
  `person_id` varchar(30) NOT NULL,
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
--  Table structure for `person_info_copy`
-- ----------------------------
DROP TABLE IF EXISTS `person_info_copy`;
CREATE TABLE `person_info_copy` (
  `person_id` varchar(30) NOT NULL,
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
  CONSTRAINT `person_info_copy_ibfk_1` FOREIGN KEY (`level`) REFERENCES `level_info` (`level_name`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `person_info_copy_ibfk_2` FOREIGN KEY (`office`) REFERENCES `office_info` (`office_name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `resume_work`
-- ----------------------------
DROP TABLE IF EXISTS `resume_work`;
CREATE TABLE `resume_work` (
  `leave_serialnumber` varchar(100) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `person_id` varchar(30) DEFAULT NULL,
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
  KEY `resume_work_leave_serialnumber` (`leave_serialnumber`),
  KEY `resume_work_person_id` (`person_id`),
  KEY `resume_work_leave_type` (`leave_type`),
  CONSTRAINT `resume_work_leave_serialnumber` FOREIGN KEY (`leave_serialnumber`) REFERENCES `approval_backups` (`serialnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
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
--  Records 
-- ----------------------------
INSERT INTO `approval` VALUES ('T2021010211235688646','请假者2','cs002','调休','2021-01-02','10','领导2','调休测试事由','批准者2','起始地2','到达地2','请假者2备注内容','2021-02-03','操作者2'), ('T2021020305123654894','请假者3','cs003','轮休','2021-02-03','7','领导3','轮休测试事由','批准者3','起始地3','到达地3','请假者3备注内容','2021-03-04','操作者3'), ('T2021030405033325641','请假者4','cs004','产假','2021-03-04','22','领导4','产假测试事由','批准者4','起始地4','到达地4','请假者4备注内容','2021-04-05','操作者4'), ('T2021040515423566846','请假者5','cs005','病假','2021-04-05','66','领导5','病假测试事由','批准者5','起始地5','到达地5','请假者5备注内容','2021-05-06','操作者5'), ('T2021050632550598568','请假者6','cs006','培训','2021-05-06','32','领导6','培训测试事由','批准者6','起始地6','到达地6','请假者6备注内容','2021-06-07','操作者6'), ('T2021060715113351598','请假者7','cs007','陪产假','2021-06-07','12','领导7','陪产假测试事由','批准者7','起始地7','到达地7','请假者7备注内容','2021-11-04','操作者7'), ('T2021110405352655856','请假者1','cs001','年休假','2021-11-04','40','领导1','年休假测试事由','批准者1','起始地1','到达地1','请假者1备注内容','2021-12-05','操作者1');
INSERT INTO `approval_backups` VALUES ('T2021010211235688646','请假者2','cs002','调休','2021-01-02','10','领导2','调休测试事由','批准者2','起始地2','到达地2','请假者2备注内容','2021-02-03','同意','操作者2'), ('T2021020305123654894','请假者3','cs003','轮休','2021-02-03','7','领导3','轮休测试事由','批准者3','起始地3','到达地3','请假者3备注内容','2021-03-04','不同意','操作者3'), ('T2021030405033325641','请假者4','cs004','产假','2021-03-04','22','领导4','产假测试事由','批准者4','起始地4','到达地4','请假者4备注内容','2021-04-05','不同意','操作者4'), ('T2021040515423566846','请假者5','cs005','病假','2021-04-05','66','领导5','病假测试事由','批准者5','起始地5','到达地5','请假者5备注内容','2021-05-06','同意','操作者5'), ('T2021050632550598568','请假者6','cs006','培训','2021-05-06','32','领导6','培训测试事由','批准者6','起始地6','到达地6','请假者6备注内容','2021-06-07','不同意','操作者6'), ('T2021060715113351598','请假者7','cs007','陪产假','2021-06-07','12','领导7','陪产假测试事由','批准者7','起始地7','到达地7','请假者7备注内容','2021-11-04','同意','操作者7'), ('T2021110405352655856','请假者1','cs001','年休假','2021-11-04','40','领导1','年休假测试事由','批准者1','起始地1','到达地1','请假者1备注内容','2021-12-05','同意','操作者1');
INSERT INTO `history_info` VALUES ('T2021110405352655856','请假者1','cs001','年休假','2021-11-04','40','领导1','年休假测试事由','批准者1','起始地1','到达地1','请假者1备注内容','2021-12-05','请假操作者1','2021-12-01','请假者1销假备注','销假操作者1','35'), ('T2021010211235688646','请假者2','cs002','调休','2021-01-02','10','领导2','调休测试事由','批准者2','起始地2','到达地2','请假者2备注内容','2021-02-03','请假操作者2','2021-02-03','请假者2销假备注','销假操作者2','30'), ('T2021020305123654894','请假者3','cs003','轮休','2021-02-03','7','领导3','轮休测试事由','批准者3','起始地3','到达地3','请假者3备注内容','2021-03-04','请假操作者3','2021-02-23','请假者2销假备注','销假操作者2','25'), ('T2021030405033325641','请假者4','cs004','产假','2021-03-04','22','领导4','产假测试事由','批准者4','起始地4','到达地4','请假者4备注内容','2021-04-05','请假操作者4','2021-04-01','请假者4销假备注','销假操作者4','26');
INSERT INTO `leader_subordinate` VALUES ('CS001','CS002'), ('CS003','CS002'), ('CS004','CS002'), ('CS001','CS003'), ('CS002','CS004'), ('CS006','CS005'), ('CS007','CS006');
INSERT INTO `leave_type` VALUES ('产假'), ('培训'), ('年休假'), ('病假'), ('调休'), ('轮休'), ('陪产假');
INSERT INTO `level_info` VALUES ('一级主任科员'), ('一级科员'), ('三级主任科员'), ('二级主任科员'), ('二级科员'), ('四级主任科员');
INSERT INTO `nation_info` VALUES ('东乡族'), ('乌孜别克族'), ('京族'), ('仡佬族'), ('仫佬族'), ('佤族'), ('侗族'), ('俄罗斯族'), ('保安族'), ('傣族'), ('僳僳族'), ('哈尼族'), ('哈萨克族'), ('回族'), ('土家族'), ('土族'), ('基诺族'), ('塔吉克族'), ('塔塔尔族'), ('壮族'), ('布依族'), ('布朗族'), ('彝族'), ('德昂族'), ('怒族'), ('拉祜族'), ('撒拉族'), ('普米族'), ('景颇族'), ('朝鲜族'), ('柯尔克孜族'), ('毛南族'), ('水族'), ('汉族'), ('满族'), ('独龙族'), ('珞巴族'), ('瑶族'), ('畲族'), ('白族'), ('纳西族'), ('维吾尔族'), ('羌族'), ('苗族'), ('蒙古族'), ('藏族'), ('裕固族'), ('赫哲族'), ('达斡尔族'), ('鄂伦春族'), ('鄂温克族'), ('锡伯族'), ('门巴族'), ('阿昌族'), ('高山族'), ('黎族');
INSERT INTO `office_info` VALUES ('仁钦则乡人民政府'), ('县委办'), ('县委政法委'), ('县委组织部'), ('县纪委'), ('政府办');
INSERT INTO `person_info` VALUES ('CS001','测试1','汉族','男','1994-05-17','四川省成都市青羊区','仁钦则乡人民政府','党委副书记、政府乡长','一级主任科员','18089922200','50','二类区'), ('CS002','测试2','藏族','女','1995-06-23','西藏自治区日喀则市谢通门县','县委办','县委办主任','二级主任科员','18289922345','40','四类区'), ('CS003','测试3','壮族','女','1996-07-24','广西壮族自治区桂林市','县委组织部','县委组织部副部长','三级主任科员','13689559706','60','二类区'), ('CS004','测试4','蒙古族','男','1974-05-25','内蒙古自治区呼和浩特市','县委政法委','县委政法委工作人员','四级主任科员','13755803250','70','三类区'), ('CS005','测试5','维吾尔族','男','1986-01-22','新疆维吾尔自治区乌鲁木齐市','县委办','县委办科员','一级科员','18735980966','70','三类区'), ('CS006','测试6','白族','女','1988-09-23','云南省大理白族自治州','政府办','政府办科员','一级科员','13580979985','50','二类区'), ('CS007','测试7','满族','男','1989-09-18','北京市东城区','县委办','县委办工作人员','四级主任科员','15233654985','50','三类区'), ('CS008','测试8','傣族','男','1980-10-01','云南省西双版纳傣族自治州','政府办','政府办主任','一级主任科员','13955895653','50','四类区'), ('CTF8135485372014','陈天飞','东乡族','男','2021-11-14','西藏自治区日喀则市南木林县','仁钦则乡人民政府','书记','四级主任科员','18089922014','50','二类区'), ('CTQ2397841386654','陈堂清','仡佬族','男','2021-11-29','西藏自治区日喀则市萨迦县','县委组织部','书记','一级科员','17899886654','60','四类区'), ('CY3807631815563','陈一','仫佬族','女','2021-11-14','西藏自治区日喀则市桑珠孜区','仁钦则乡人民政府','书记','四级主任科员','18099885563','20','二类区'), ('MHY6977203825564','毛红英','乌孜别克族','女','2021-11-14','西藏自治区日喀则市南木林县','县纪委','书记','三级主任科员','18099885564','20','四类区'), ('XYT4299500182022','夏雨婷','京族','男','2021-11-26','西藏自治区日喀则市南木林县','仁钦则乡人民政府','书记','四级主任科员','18089922022','50','二类区');
INSERT INTO `person_info_copy` VALUES ('CS001','测试1','汉族','男','1994-05-17','四川省成都市青羊区','仁钦则乡人民政府','党委副书记、政府乡长','一级主任科员','18089922200','50','二类区'), ('CS002','测试2','藏族','女','1995-06-23','西藏自治区日喀则市谢通门县','县委办','县委办主任','二级主任科员','18289922345','40','四类区'), ('CS003','测试3','壮族','女','1996-07-24','广西自治区桂林市','县委组织部','县委组织部副部长','三级主任科员','13689559706','60','二类区'), ('CS004','测试4','蒙古族','男','1974-05-25','内蒙古自治区呼和浩特市','县委政法委','县委政法委工作人员','四级主任科员','13755803250','70','三类区'), ('CS005','测试5','维吾尔族','男','1986-01-22','新疆自治区乌鲁木齐市','县委办','县委办科员','一级科员','18735980966','70','三类区'), ('CS006','测试6','白族','女','1988-09-23','云南省大理州','政府办','政府办科员','一级科员','13580979985','50','二类区'), ('CS007','测试7','满族','男','1989-09-18','北京市东城区','县委办','县委办工作人员','四级主任科员','15233654985','50','三类区'), ('CS008','测试8','傣族','男','1980-10-01','云南省西双版纳州','政府办','政府办主任','一级主任科员','13955895653','50','四类区'), ('CTF8135485372014','陈天飞','东乡族','男','2021-11-14','西藏自治区日喀则市南木林县','仁钦则乡人民政府','书记','四级主任科员','18089922014','50','二类区'), ('CTQ2397841386654','陈堂清','仡佬族','男','2021-11-29','西藏自治区日喀则市萨迦县','县委组织部','书记','一级科员','17899886654','60','四类区'), ('CY3807631815563','陈一','仫佬族','女','2021-11-14','西藏自治区日喀则市桑珠孜区','仁钦则乡人民政府','书记','四级主任科员','18099885563','20','二类区'), ('MHY6977203825564','毛红英','乌孜别克族','女','2021-11-14','西藏自治区日喀则市南木林县','县纪委','书记','三级主任科员','18099885564','20','四类区'), ('XYT4299500182022','夏雨婷','京族','男','2021-11-26','西藏自治区日喀则市南木林县','仁钦则乡人民政府','书记','四级主任科员','18089922022','50','二类区');
INSERT INTO `resume_work` VALUES ('T2021110405352655856','请假者1','cs001','年休假','2021-11-04','40','领导1','年休假测试事由','批准者1','起始地1','到达地1','请假者1备注内容','2021-12-05','请假操作者1'), ('T2021010211235688646','请假者2','cs002','调休','2021-01-02','10','领导2','调休测试事由','批准者2','起始地2','到达地2','请假者2备注内容','2021-02-03','请假操作者2'), ('T2021020305123654894','请假者3','cs003','轮休','2021-02-03','7','领导3','轮休测试事由','批准者3','起始地3','到达地3','请假者3备注内容','2021-03-04','请假操作者3'), ('T2021030405033325641','请假者4','cs004','产假','2021-03-04','22','领导4','产假测试事由','批准者4','起始地4','到达地4','请假者4备注内容','2021-04-05','请假操作者4'), ('T2021040515423566846','请假者5','cs005','病假','2021-04-05','66','领导5','病假测试事由','批准者5','起始地5','到达地5','请假者5备注内容','2021-05-06','请假操作者5'), ('T2021050632550598568','请假者6','cs006','培训','2021-05-06','32','领导6','培训测试事由','批准者6','起始地6','到达地6','请假者6备注内容','2021-06-07','请假操作者6'), ('T2021060715113351598','请假者7','cs007','陪产假','2021-06-07','12','领导7','陪产假测试事由','批准者7','起始地7','到达地7','请假者7备注内容','2021-11-04','请假操作者7');
