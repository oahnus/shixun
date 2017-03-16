CREATE DATABASE IF NOT EXISTS shixun_17_02_26;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '管理员');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '观海楼', '王某某', '江苏镇江');
INSERT INTO `company` VALUES ('2', '船院空调', '郭某某', '江苏镇江');
INSERT INTO `company` VALUES ('3', '镇江旅游', '徐某某', '江苏镇江');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_num` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `profession` varchar(255) NOT NULL,
  `depart` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_student_id` (`student_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '13421', '张三', '轮机', '船海');
INSERT INTO `student` VALUES ('2', '13422', '李四', '软件', '计算机');
INSERT INTO `student` VALUES ('3', '13423', '王五', '自动化', '电信');
INSERT INTO `student` VALUES ('4', '13424', '赵六', '软件', '计算机');
INSERT INTO `student` VALUES ('5', '13425', '徐七', '计科', '计算机');
INSERT INTO `student` VALUES ('8', '1341901120', 'admin', '计算机', '计算机');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `worker_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `profession` varchar(255) NOT NULL,
  `depart` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_worker_id` (`worker_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('29', '111111', '赵', '计算机', '计算机');
INSERT INTO `teacher` VALUES ('30', '111112', '钱', '软件', '计算机');
INSERT INTO `teacher` VALUES ('31', '111116', '李', '物联网', '计算机');
INSERT INTO `teacher` VALUES ('32', '111115', '郭', '通信', '计算机');
INSERT INTO `teacher` VALUES ('33', '111117', '赵强', '通信', '计算机');
INSERT INTO `teacher` VALUES ('34', '112323', '王', '物联网', '计算机');

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '0');
INSERT INTO `user_auth` VALUES ('2', '1341901120', 'ee97401d19107838eb978f461e354afd', '3');
INSERT INTO `user_auth` VALUES ('3', '13141101', 'fb8a249b6e84d9d9f63adbf144b2efc7', '2');
