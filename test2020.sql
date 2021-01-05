/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : test2020

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-01-05 13:19:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alertstudent
-- ----------------------------
DROP TABLE IF EXISTS `alertstudent`;
CREATE TABLE `alertstudent` (
  `studentNo` char(12) NOT NULL,
  `studentName` varchar(30) NOT NULL,
  `termYear` char(10) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  `class` varchar(50) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `tutor` varchar(40) DEFAULT NULL,
  `spouse` varchar(50) DEFAULT NULL,
  `MZ` varchar(20) DEFAULT NULL,
  `ZZMM` varchar(20) DEFAULT NULL,
  `JG` varchar(50) DEFAULT NULL,
  `personID` char(18) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `QQ` varchar(15) DEFAULT NULL,
  `fatherName` varchar(30) DEFAULT NULL,
  `fatherPhone` char(11) DEFAULT NULL,
  `fatherWorkplace` varchar(100) DEFAULT NULL,
  `motherName` varchar(30) DEFAULT NULL,
  `motherPhone` char(11) DEFAULT NULL,
  `motherWorkplace` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `buildingName` varchar(20) DEFAULT NULL,
  `roomNum` char(10) DEFAULT NULL,
  `bedNum` char(10) DEFAULT NULL,
  `TC` varchar(200) DEFAULT NULL,
  `SFPS` tinyint(4) DEFAULT '0',
  `studyType` varchar(20) DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`studentNo`),
  UNIQUE KEY `studentNo_UNIQUE` (`studentNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for alumni
-- ----------------------------
DROP TABLE IF EXISTS `alumni`;
CREATE TABLE `alumni` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `deeds` varchar(5000) DEFAULT NULL COMMENT '校友事迹',
  `imagePath` varchar(40) DEFAULT NULL COMMENT '图片路径',
  `lve` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='校友风采表';

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) NOT NULL DEFAULT '0',
  `answer` varchar(255) DEFAULT NULL,
  `studentNo` char(12) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `qid` (`qid`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` char(12) NOT NULL DEFAULT '',
  `termYear` char(4) NOT NULL DEFAULT '2016',
  `major` varchar(15) DEFAULT NULL,
  `className` varchar(15) NOT NULL DEFAULT '',
  `courseName` varchar(20) DEFAULT NULL,
  `credit` float(2,1) NOT NULL DEFAULT '0.0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=616 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for deduction
-- ----------------------------
DROP TABLE IF EXISTS `deduction`;
CREATE TABLE `deduction` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT 'null',
  `score` double(4,2) NOT NULL DEFAULT '0.00',
  `bz` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `monitorNo` varchar(12) DEFAULT NULL COMMENT '班长学号',
  `studentNo` varchar(12) NOT NULL DEFAULT '' COMMENT '学生学号',
  `P_moral` varchar(255) DEFAULT NULL COMMENT '德育分加分反馈',
  `D_moral` varchar(255) DEFAULT NULL COMMENT '德育扣分反馈',
  `isRead` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录消息是否班长已读',
  PRIMARY KEY (`studentNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `fileName` varchar(80) NOT NULL DEFAULT '',
  `stuType` varchar(10) NOT NULL DEFAULT '本科生'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filledout
-- ----------------------------
DROP TABLE IF EXISTS `filledout`;
CREATE TABLE `filledout` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL DEFAULT '0',
  `qnid` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `qnid` (`qnid`),
  CONSTRAINT `filledout_ibfk_1` FOREIGN KEY (`qnid`) REFERENCES `questionnaire` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `studentNo` char(12) NOT NULL,
  `termNo` char(10) NOT NULL,
  `avgGrade` double(4,2) DEFAULT NULL,
  `avgCPA` double(4,2) DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`studentNo`,`termNo`),
  KEY `FK_gt_idx` (`termNo`),
  CONSTRAINT `FK_gs` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`),
  CONSTRAINT `FK_gt` FOREIGN KEY (`termNo`) REFERENCES `term` (`termNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jl
-- ----------------------------
DROP TABLE IF EXISTS `jl`;
CREATE TABLE `jl` (
  `JLId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL,
  `JLname` varchar(50) NOT NULL,
  `JLlevel` varchar(50) NOT NULL,
  `getTime` varchar(20) DEFAULT NULL,
  `adviser` varchar(10) DEFAULT NULL,
  `sponsor` varchar(55) DEFAULT NULL,
  `termOrindividual` varchar(6) DEFAULT NULL,
  `imageName` varchar(24) DEFAULT NULL,
  `stuType` varchar(255) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`JLId`),
  UNIQUE KEY `JLid_UNIQUE` (`JLId`),
  KEY `FK_JL_idx` (`studentNo`),
  CONSTRAINT `FK_JL` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3142 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for journalinfo
-- ----------------------------
DROP TABLE IF EXISTS `journalinfo`;
CREATE TABLE `journalinfo` (
  `journalId` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(12) NOT NULL,
  `journalName` varchar(50) NOT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '研究生',
  PRIMARY KEY (`journalId`),
  UNIQUE KEY `journalId_UNIQUE` (`journalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for leavetip
-- ----------------------------
DROP TABLE IF EXISTS `leavetip`;
CREATE TABLE `leavetip` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `classno` varchar(255) DEFAULT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `studenttel` varchar(255) DEFAULT NULL,
  `dormitory` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `gowhere` varchar(255) DEFAULT NULL,
  `dayofleave` varchar(255) DEFAULT NULL,
  `parenttel` varchar(255) DEFAULT NULL,
  `leavebegin` varchar(255) DEFAULT NULL,
  `leaveend` varchar(255) DEFAULT NULL,
  `leavedate` varchar(255) DEFAULT NULL,
  `approve` varchar(255) DEFAULT '0',
  `status` char(1) DEFAULT '0',
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  `userName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lecture
-- ----------------------------
DROP TABLE IF EXISTS `lecture`;
CREATE TABLE `lecture` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` char(10) NOT NULL DEFAULT '2018-2019',
  `title` varchar(100) DEFAULT NULL COMMENT '讲座主题',
  `holdTime` varchar(20) DEFAULT NULL,
  `location` varchar(40) DEFAULT NULL,
  `speaker` varchar(255) DEFAULT NULL,
  `introduction` varchar(600) DEFAULT NULL,
  `deadlineTime` datetime DEFAULT NULL,
  `limitNumber` int(11) DEFAULT NULL,
  `publisher` varchar(20) DEFAULT NULL,
  `releaseTime` datetime DEFAULT NULL,
  `speakerIntroduction` varchar(600) DEFAULT NULL,
  `imagePath` varchar(40) DEFAULT NULL,
  `priority` int(11) NOT NULL DEFAULT '1' COMMENT '优先级',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='讲座信息表';

-- ----------------------------
-- Table structure for monitor
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` varchar(12) NOT NULL DEFAULT '',
  `studentName` varchar(50) NOT NULL DEFAULT 'xxx',
  `nianji` varchar(5) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `className` int(11) DEFAULT NULL,
  `bz` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='班长信息表';

-- ----------------------------
-- Table structure for moral
-- ----------------------------
DROP TABLE IF EXISTS `moral`;
CREATE TABLE `moral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '',
  `mid` int(11) NOT NULL DEFAULT '0',
  `score` double(4,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for moralsummary
-- ----------------------------
DROP TABLE IF EXISTS `moralsummary`;
CREATE TABLE `moralsummary` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` varchar(12) NOT NULL DEFAULT '',
  `studentNo` varchar(12) NOT NULL DEFAULT '',
  `selfEvaluation` double(5,2) DEFAULT NULL,
  `classEvaluation` double(5,2) DEFAULT NULL,
  `teacherEvaluation` double(5,2) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=743 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for othxy
-- ----------------------------
DROP TABLE IF EXISTS `othxy`;
CREATE TABLE `othxy` (
  `xyid` int(2) NOT NULL AUTO_INCREMENT,
  `othxyname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`xyid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for othzy
-- ----------------------------
DROP TABLE IF EXISTS `othzy`;
CREATE TABLE `othzy` (
  `zyid` int(11) NOT NULL AUTO_INCREMENT,
  `xyid` int(11) DEFAULT NULL,
  `zyname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`zyid`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for persondeduction
-- ----------------------------
DROP TABLE IF EXISTS `persondeduction`;
CREATE TABLE `persondeduction` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` char(10) NOT NULL DEFAULT '2018-2019',
  `studentNo` char(12) NOT NULL DEFAULT '201688888888',
  `did` int(11) NOT NULL DEFAULT '0',
  `times` int(11) NOT NULL DEFAULT '1' COMMENT '次数',
  PRIMARY KEY (`Id`),
  KEY `did` (`did`),
  CONSTRAINT `persondeduction_ibfk_1` FOREIGN KEY (`did`) REFERENCES `deduction` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for personknowledge
-- ----------------------------
DROP TABLE IF EXISTS `personknowledge`;
CREATE TABLE `personknowledge` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL DEFAULT '0',
  `cid` int(11) NOT NULL DEFAULT '0' COMMENT '专业课程id',
  `score` int(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `studentNo` (`studentNo`),
  KEY `cid` (`cid`),
  CONSTRAINT `personknowledge_ibfk_1` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`),
  CONSTRAINT `personknowledge_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `course` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for personmoral
-- ----------------------------
DROP TABLE IF EXISTS `personmoral`;
CREATE TABLE `personmoral` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` varchar(10) DEFAULT NULL,
  `studentNo` char(12) NOT NULL DEFAULT '',
  `mid` int(11) NOT NULL DEFAULT '0' COMMENT '德育分加分类别',
  `jid` int(11) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `score` double(4,2) DEFAULT NULL,
  `getTime` varchar(25) NOT NULL DEFAULT 'xxx年xxx月xx日',
  `imagePath` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `studentNo` (`studentNo`),
  KEY `mid` (`mid`),
  CONSTRAINT `personmoral_ibfk_1` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`),
  CONSTRAINT `personmoral_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `moral` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7443 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for personsports
-- ----------------------------
DROP TABLE IF EXISTS `personsports`;
CREATE TABLE `personsports` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` char(10) NOT NULL DEFAULT '',
  `studentNo` char(12) NOT NULL DEFAULT '',
  `firstTerm` double(5,2) DEFAULT NULL,
  `secondTerm` double(5,2) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `studentNo` (`studentNo`),
  CONSTRAINT `personsports_ibfk_1` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`)
) ENGINE=InnoDB AUTO_INCREMENT=682 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for personsummary
-- ----------------------------
DROP TABLE IF EXISTS `personsummary`;
CREATE TABLE `personsummary` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` char(10) NOT NULL DEFAULT '',
  `studentNo` char(12) NOT NULL DEFAULT '0',
  `moral` double(4,2) DEFAULT '0.00',
  `knowledge` double(4,2) DEFAULT '0.00',
  `sports` double(4,2) DEFAULT '0.00',
  `deduction` double(4,2) DEFAULT '0.00',
  `fails` int(11) DEFAULT '0',
  `bz` varchar(40) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `Tban` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `studentNo` (`studentNo`),
  CONSTRAINT `personsummary_ibfk_1` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`)
) ENGINE=InnoDB AUTO_INCREMENT=762 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for practice
-- ----------------------------
DROP TABLE IF EXISTS `practice`;
CREATE TABLE `practice` (
  `practiceId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL,
  `practiceName` varchar(100) NOT NULL,
  `type` char(20) NOT NULL,
  `startTime` varchar(20) DEFAULT NULL,
  `endTime` varchar(20) DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`practiceId`),
  UNIQUE KEY `practiceId_UNIQUE` (`practiceId`),
  KEY `FK_practice_idx` (`studentNo`),
  CONSTRAINT `FK_practice` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1113 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `projectId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL,
  `projectName` varchar(50) NOT NULL,
  `time` varchar(20) DEFAULT NULL,
  `isFirstCharge` varchar(4) DEFAULT '否',
  `image` varchar(20) DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '研究生',
  PRIMARY KEY (`projectId`),
  UNIQUE KEY `projectId_UNIQUE` (`projectId`),
  KEY `FK_project_idx` (`studentNo`),
  CONSTRAINT `FK_project` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for proportion
-- ----------------------------
DROP TABLE IF EXISTS `proportion`;
CREATE TABLE `proportion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` char(12) NOT NULL DEFAULT '0',
  `moralPer` double(3,2) NOT NULL DEFAULT '0.00',
  `knowledgePer` double(3,2) NOT NULL DEFAULT '0.00',
  `sportsPer` double(3,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pstudent
-- ----------------------------
DROP TABLE IF EXISTS `pstudent`;
CREATE TABLE `pstudent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pstudentNo` char(12) NOT NULL,
  `SFZK` tinyint(4) NOT NULL DEFAULT '0',
  `ZZname` varchar(45) NOT NULL,
  `ZZtime` date DEFAULT NULL,
  `ZZmoney` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FK_PStudent_idx` (`pstudentNo`),
  CONSTRAINT `FK_PStudent` FOREIGN KEY (`pstudentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for punish
-- ----------------------------
DROP TABLE IF EXISTS `punish`;
CREATE TABLE `punish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL,
  `punishName` varchar(50) NOT NULL,
  `punishReason` varchar(100) DEFAULT NULL,
  `punishTime` date DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FK_punish_idx` (`studentNo`),
  CONSTRAINT `FK_punish` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qnid` int(11) NOT NULL DEFAULT '0',
  `questionName` varchar(40) NOT NULL DEFAULT '0',
  `introduce` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `qnid` (`qnid`),
  KEY `questionName` (`questionName`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`qnid`) REFERENCES `questionnaire` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `questionnaireName` varchar(50) NOT NULL DEFAULT '0',
  `termYear` char(5) NOT NULL DEFAULT '0',
  `releaseTime` date NOT NULL DEFAULT '1999-09-09',
  `bz` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for registration
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL DEFAULT '201644078888',
  `lid` int(11) NOT NULL DEFAULT '0',
  `registrationTime` datetime DEFAULT '0000-00-00 00:00:00',
  `status` varchar(10) NOT NULL DEFAULT '报名成功',
  PRIMARY KEY (`Id`),
  KEY `studentNo` (`studentNo`),
  KEY `registration_ibfk_1` (`lid`),
  CONSTRAINT `registration_ibfk_1` FOREIGN KEY (`lid`) REFERENCES `lecture` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `registration_ibfk_2` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`)
) ENGINE=InnoDB AUTO_INCREMENT=520 DEFAULT CHARSET=utf8 COMMENT='报名表';

-- ----------------------------
-- Table structure for rewardinfo
-- ----------------------------
DROP TABLE IF EXISTS `rewardinfo`;
CREATE TABLE `rewardinfo` (
  `rewardId` int(11) NOT NULL AUTO_INCREMENT,
  `rewardTime` char(12) NOT NULL,
  `rewardName` varchar(50) NOT NULL,
  `rewardLevel` varchar(50) NOT NULL,
  `sponsor` varchar(50) NOT NULL,
  `termOrindividual` varchar(10) DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`rewardId`),
  UNIQUE KEY `rewardId_UNIQUE` (`rewardId`)
) ENGINE=InnoDB AUTO_INCREMENT=7567 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for schoolmate
-- ----------------------------
DROP TABLE IF EXISTS `schoolmate`;
CREATE TABLE `schoolmate` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `termYear` varchar(8) DEFAULT NULL,
  `major` varchar(15) DEFAULT NULL,
  `graduationYear` varchar(8) DEFAULT '' COMMENT '毕业年份',
  `area` varchar(50) DEFAULT NULL COMMENT '所在地区',
  `alumniAssociation` varchar(20) DEFAULT NULL COMMENT '所属校友会',
  `alu_position` varchar(20) DEFAULT NULL COMMENT '校友会职务',
  `workUnit` varchar(40) DEFAULT NULL COMMENT '工作单位',
  `position` varchar(40) DEFAULT NULL COMMENT '职位',
  `phone` varchar(14) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `QQ` varchar(40) DEFAULT NULL COMMENT 'QQ',
  `wechatId` varchar(20) DEFAULT NULL COMMENT '微信号',
  `bz` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1867 DEFAULT CHARSET=utf8 COMMENT='校友信息表';

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill` (
  `skillId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL,
  `skillName` varchar(50) NOT NULL,
  `skillType` varchar(40) DEFAULT NULL,
  `gettime` varchar(20) DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`skillId`),
  UNIQUE KEY `skillid_UNIQUE` (`skillId`),
  KEY `FK_SKILL_idx` (`studentNo`),
  CONSTRAINT `FK_SKILL` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentNo` char(12) NOT NULL,
  `studentName` varchar(30) NOT NULL,
  `termYear` char(10) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  `class` int(4) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `tutor` varchar(255) DEFAULT NULL,
  `MZ` varchar(20) DEFAULT NULL,
  `ZZMM` varchar(20) DEFAULT NULL,
  `JG` varchar(50) DEFAULT NULL,
  `personID` char(18) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `QQ` varchar(15) DEFAULT NULL,
  `spouse` varchar(50) DEFAULT NULL,
  `fatherName` varchar(30) DEFAULT NULL,
  `fatherPhone` char(11) DEFAULT NULL,
  `fatherWorkplace` varchar(100) DEFAULT NULL,
  `motherName` varchar(30) DEFAULT NULL,
  `motherPhone` char(11) DEFAULT NULL,
  `motherWorkplace` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `buildingName` varchar(20) DEFAULT NULL,
  `roomNum` char(10) DEFAULT NULL,
  `bedNum` char(10) DEFAULT NULL,
  `TC` varchar(200) DEFAULT NULL,
  `SFPS` tinyint(4) DEFAULT '0',
  `studyType` varchar(20) DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`studentNo`),
  UNIQUE KEY `studentNo_UNIQUE` (`studentNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for studentgrade
-- ----------------------------
DROP TABLE IF EXISTS `studentgrade`;
CREATE TABLE `studentgrade` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ranking` int(5) NOT NULL,
  `studentNo` varchar(15) NOT NULL,
  `studentName` varchar(15) NOT NULL,
  `sex` varchar(4) NOT NULL,
  `xuenian` varchar(20) DEFAULT NULL,
  `xueqi` varchar(20) DEFAULT NULL,
  `nianji` varchar(10) DEFAULT NULL,
  `major` varchar(24) DEFAULT NULL,
  `class` varchar(40) DEFAULT NULL,
  `courseCount` int(4) DEFAULT NULL,
  `fail` int(4) DEFAULT '0',
  `credit` float(4,1) DEFAULT NULL,
  `getCredit` float(4,1) DEFAULT NULL,
  `GPA` float(4,2) DEFAULT NULL,
  `creditGPA` float(6,2) DEFAULT NULL,
  `avgCreditGPA` float(6,2) DEFAULT NULL,
  `avgGrade` float(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=725 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for submissions
-- ----------------------------
DROP TABLE IF EXISTS `submissions`;
CREATE TABLE `submissions` (
  `submissionId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL,
  `articleName` varchar(100) NOT NULL,
  `type` char(20) NOT NULL,
  `time` varchar(20) DEFAULT NULL,
  `periodical` varchar(20) DEFAULT NULL,
  `image` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`submissionId`),
  UNIQUE KEY `practiceId_UNIQUE` (`submissionId`),
  KEY `FK_submissions_idx` (`studentNo`),
  CONSTRAINT `FK_submissions` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacherinfo
-- ----------------------------
DROP TABLE IF EXISTS `teacherinfo`;
CREATE TABLE `teacherinfo` (
  `teacherId` int(10) NOT NULL AUTO_INCREMENT,
  `teacherName` varchar(20) NOT NULL,
  `position` varchar(40) DEFAULT NULL,
  `officeSpace` varchar(100) DEFAULT NULL,
  `responsibility` varchar(255) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`teacherId`),
  UNIQUE KEY `teacherId_UNIQUE` (`teacherId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for term
-- ----------------------------
DROP TABLE IF EXISTS `term`;
CREATE TABLE `term` (
  `termNo` char(10) NOT NULL,
  `termName` varchar(20) NOT NULL,
  PRIMARY KEY (`termNo`),
  UNIQUE KEY `termNo_UNIQUE` (`termNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` char(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `userType` varchar(255) NOT NULL DEFAULT 'student' COMMENT '默认角色为学生',
  PRIMARY KEY (`userName`),
  UNIQUE KEY `student_UNIQUE` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xjyd
-- ----------------------------
DROP TABLE IF EXISTS `xjyd`;
CREATE TABLE `xjyd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL,
  `YDtime` varchar(20) DEFAULT NULL,
  `YDreason` varchar(100) DEFAULT NULL,
  `BZ` varchar(100) DEFAULT NULL,
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FK_XJYD_idx` (`studentNo`),
  CONSTRAINT `FK_XJYD` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xjzc
-- ----------------------------
DROP TABLE IF EXISTS `xjzc`;
CREATE TABLE `xjzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNo` char(12) NOT NULL,
  `ZCyear` char(10) NOT NULL,
  `term` varchar(20) NOT NULL,
  `ZCorNot` char(1) DEFAULT '0',
  `stuType` varchar(10) NOT NULL DEFAULT '本科生',
  PRIMARY KEY (`id`),
  KEY `FK_XJZC_idx` (`studentNo`),
  CONSTRAINT `FK_XJZC` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=619 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zc_control
-- ----------------------------
DROP TABLE IF EXISTS `zc_control`;
CREATE TABLE `zc_control` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xuenian` varchar(20) NOT NULL,
  `zc_switch` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for view_all
-- ----------------------------
DROP VIEW IF EXISTS `view_all`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_all` AS select `s`.`studentNo` AS `studentNo`,`s`.`studentName` AS `studentName`,`s`.`termYear` AS `termYear`,`s`.`major` AS `major`,`s`.`class` AS `class`,`s`.`sex` AS `sex`,`s`.`MZ` AS `MZ`,`s`.`ZZMM` AS `ZZMM`,`s`.`JG` AS `JG`,`s`.`personID` AS `personID`,`s`.`phone` AS `phone`,`s`.`QQ` AS `QQ`,`s`.`fatherName` AS `fatherName`,`s`.`fatherPhone` AS `fatherPhone`,`s`.`motherName` AS `motherName`,`s`.`motherPhone` AS `motherPhone`,`s`.`address` AS `address`,`s`.`buildingName` AS `buildingName`,`s`.`fatherWorkplace` AS `fatherWorkplace`,`s`.`motherWorkplace` AS `motherWorkplace`,`s`.`studyType` AS `studyType`,`s`.`roomNum` AS `roomNum`,`s`.`bedNum` AS `bedNum`,`s`.`TC` AS `TC`,`s`.`SFPS` AS `SFPS`,`s`.`stuType` AS `stuType` from `student` `s` ;

-- ----------------------------
-- View structure for view_count_reward
-- ----------------------------
DROP VIEW IF EXISTS `view_count_reward`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_count_reward` AS select `s`.`termYear` AS `nianji`,`r`.`rewardTime` AS `xuenian`,`s`.`major` AS `major`,`r`.`rewardLevel` AS `level`,`r`.`rewardName` AS `name`,`j`.`adviser` AS `adviser`,`r`.`sponsor` AS `sponsor` from ((`student` `s` join `jl` `j`) join `rewardinfo` `r`) where ((`s`.`studentNo` = `j`.`studentNo`) and (`j`.`JLlevel` = `r`.`rewardLevel`) and (`j`.`JLname` = `r`.`rewardName`) and (`j`.`sponsor` = `r`.`sponsor`)) ;

-- ----------------------------
-- View structure for view_jl
-- ----------------------------
DROP VIEW IF EXISTS `view_jl`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_jl` AS select `s`.`termYear` AS `termYear`,`s`.`major` AS `major`,`s`.`class` AS `class`,`j`.`JLId` AS `JLId`,`j`.`studentNo` AS `studentNo`,`s`.`studentName` AS `studentName`,`j`.`adviser` AS `adviser`,`j`.`sponsor` AS `sponsor`,`s`.`stuType` AS `stuType`,`j`.`JLname` AS `JLname`,`j`.`JLlevel` AS `JLlevel`,`j`.`getTime` AS `getTime` from (`student` `s` join `jl` `j`) where (`s`.`studentNo` = `j`.`studentNo`) ;

-- ----------------------------
-- View structure for view_practice
-- ----------------------------
DROP VIEW IF EXISTS `view_practice`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_practice` AS select `s`.`termYear` AS `termYear`,`s`.`major` AS `major`,`s`.`class` AS `class`,`s`.`studentName` AS `studentName`,`pr`.`practiceId` AS `practiceId`,`pr`.`studentNo` AS `studentNo`,`pr`.`practiceName` AS `practiceName`,`pr`.`type` AS `type`,`pr`.`stuType` AS `stuType`,`pr`.`startTime` AS `startTime`,`pr`.`endTime` AS `endTime` from (`student` `s` join `practice` `pr`) where (`s`.`studentNo` = `pr`.`studentNo`) ;

-- ----------------------------
-- View structure for view_pstudent
-- ----------------------------
DROP VIEW IF EXISTS `view_pstudent`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_pstudent` AS select `s`.`termYear` AS `termYear`,`s`.`major` AS `major`,`s`.`class` AS `class`,`s`.`studentName` AS `studentName`,`s`.`stuType` AS `stuType`,`ps`.`id` AS `id`,`ps`.`pstudentNo` AS `pstudentNo`,`ps`.`SFZK` AS `SFZK`,`ps`.`ZZname` AS `ZZname`,`ps`.`type` AS `type`,`ps`.`ZZmoney` AS `ZZmoney`,`ps`.`ZZtime` AS `ZZtime` from (`student` `s` join `pstudent` `ps`) where (`s`.`studentNo` = `ps`.`pstudentNo`) ;

-- ----------------------------
-- View structure for view_punish
-- ----------------------------
DROP VIEW IF EXISTS `view_punish`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_punish` AS select `s`.`termYear` AS `termYear`,`s`.`major` AS `major`,`s`.`class` AS `class`,`s`.`studentName` AS `studentName`,`s`.`stuType` AS `stuType`,`p`.`id` AS `id`,`p`.`studentNo` AS `studentNo`,`p`.`punishName` AS `punishName`,`p`.`punishReason` AS `punishReason`,`p`.`punishTime` AS `punishTime` from (`student` `s` join `punish` `p`) where (`s`.`studentNo` = `p`.`studentNo`) ;

-- ----------------------------
-- View structure for view_scoreanalyses
-- ----------------------------
DROP VIEW IF EXISTS `view_scoreanalyses`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_scoreanalyses` AS select `s1`.`termYear` AS `termYear`,`s1`.`xuenian` AS `xuenian`,`s1`.`studentNo` AS `studentNo`,avg(`s1`.`score`) AS `avgScore`,std(`s1`.`score`) AS `stdScore`,max(`s1`.`score`) AS `maxScore`,min(`s1`.`score`) AS `minScore`,(select group_concat(`s2`.`courseName` separator ',') from `view_scoreview` `s2` where ((`s2`.`score` < 60) and (`s2`.`score` >= 0) and (`s1`.`studentNo` = `s2`.`studentNo`))) AS `fails`,(select count(0) from `view_scoreview` `s3` where ((`s3`.`score` < 60) and (`s3`.`score` >= 0) and (`s1`.`studentNo` = `s3`.`studentNo`))) AS `lt60`,(select count(0) from `view_scoreview` `s4` where ((`s4`.`score` between 60 and 70) and (`s1`.`studentNo` = `s4`.`studentNo`))) AS `gt60lt70`,(select count(0) from `view_scoreview` `s5` where ((`s5`.`score` between 70 and 80) and (`s1`.`studentNo` = `s5`.`studentNo`))) AS `gt70lt80`,(select count(0) from `view_scoreview` `s5` where ((`s5`.`score` between 80 and 90) and (`s1`.`studentNo` = `s5`.`studentNo`))) AS `gt80lt90`,(select count(0) from `view_scoreview` `s6` where ((`s6`.`score` >= 90) and (`s1`.`studentNo` = `s6`.`studentNo`))) AS `gt90` from `view_scoreview` `s1` where (`s1`.`score` >= 0) group by `s1`.`studentNo` having (`avgScore` <> 0) ;

-- ----------------------------
-- View structure for view_scoreview
-- ----------------------------
DROP VIEW IF EXISTS `view_scoreview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_scoreview` AS select `c`.`termYear` AS `termYear`,`c`.`xuenian` AS `xuenian`,`p`.`studentNo` AS `studentNo`,`p`.`score` AS `score`,`c`.`courseName` AS `courseName`,`c`.`credit` AS `credit` from (`personknowledge` `p` join `course` `c`) where (`p`.`cid` = `c`.`Id`) ;

-- ----------------------------
-- View structure for view_skill
-- ----------------------------
DROP VIEW IF EXISTS `view_skill`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_skill` AS select `s`.`termYear` AS `termYear`,`s`.`major` AS `major`,`s`.`class` AS `class`,`s`.`studentName` AS `studentName`,`s`.`stuType` AS `stuType`,`sk`.`skillId` AS `skillId`,`sk`.`studentNo` AS `studentNo`,`sk`.`skillName` AS `skillName`,`sk`.`skillType` AS `skillType`,`sk`.`gettime` AS `gettime` from (`student` `s` join `skill` `sk`) where (`s`.`studentNo` = `sk`.`studentNo`) ;

-- ----------------------------
-- View structure for view_xjyd
-- ----------------------------
DROP VIEW IF EXISTS `view_xjyd`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_xjyd` AS select `s`.`termYear` AS `termYear`,`s`.`studentName` AS `studentName`,`s`.`major` AS `major`,`s`.`class` AS `class`,`s`.`stuType` AS `stuType`,`xd`.`id` AS `id`,`xd`.`studentNo` AS `studentNo`,`xd`.`YDtime` AS `YDtime`,`xd`.`YDreason` AS `YDreason`,`xd`.`BZ` AS `BZ` from (`student` `s` join `xjyd` `xd`) where (`s`.`studentNo` = `xd`.`studentNo`) ;

-- ----------------------------
-- View structure for view_xjzc
-- ----------------------------
DROP VIEW IF EXISTS `view_xjzc`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_xjzc` AS select `s`.`termYear` AS `termYear`,`s`.`major` AS `major`,`s`.`class` AS `class`,`s`.`studentName` AS `studentName`,`s`.`stuType` AS `stuType`,`x`.`id` AS `id`,`s`.`studentNo` AS `studentNo`,`x`.`ZCyear` AS `ZCyear`,`x`.`term` AS `term`,`x`.`ZCorNot` AS `ZCorNot` from (`student` `s` left join `xjzc` `x` on((`s`.`studentNo` = `x`.`studentNo`))) ;
