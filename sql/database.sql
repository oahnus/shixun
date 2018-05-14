/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shixun

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-08 21:26:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '公司ID',
  `name` varchar(100) NOT NULL COMMENT '公司名称',
  `address` varchar(255) COMMENT '公司地址',
  `telephone` VARCHAR(255) COMMENT '公司联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  auth_id BIGINT NOT NULL,
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  del_flag BIT(1) NOT NULL  DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8 COMMENT='公司表';

DROP TABLE IF EXISTS company_contact;
CREATE TABLE company_contact(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(100) NOT NULL ,
  telephone VARCHAR(100) NOT NULL ,
  email VARCHAR(255) NOT NULL ,
  company_id BIGINT NOT NULL ,
  create_time DATETIME NOT NULL ,
  update_time DATETIME NOT NULL ,
  del_flag BIT(1) NOT NULL DEFAULT 0
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8;

DROP TABLE IF EXISTS admin;
CREATE TABLE admin(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(255) NOT NULL ,
  telephone VARCHAR(50) NOT NULL ,
  email VARCHAR(255) NULL,
  auth_id BIGINT NOT NULL,
  create_time DATETIME NOT NULL,
  del_flag bit(1) NOT NULL DEFAULT 0
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8;

DROP TABLE IF EXISTS user_auth;
CREATE TABLE user_auth (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(80) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role TINYINT NOT NULL DEFAULT 4 COMMENT '用户角色id 4 student, 3 teacher, 2 company, 1 admin',
  create_time DATETIME NOT NULL ,
  del_flag BIT(1) NOT NULL DEFAULT 0,
  UNIQUE KEY idx_username(username, role)
)ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARSET = utf8 COMMENT '登录权限表';

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(100) NOT NULL COMMENT '角色名称'
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8 COMMENT '角色表';

INSERT INTO user_role(name) VALUES ('管理员'), ('企业'), ('教师'), ('学生');

# todo 添加学院 院长，指导老师等字段
DROP TABLE IF EXISTS department;
CREATE TABLE department (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(255) NOT NULL ,
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位'
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8 COMMENT '学院表';

DROP TABLE IF EXISTS profession;
CREATE TABLE profession (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(255) NOT NULL ,
  depart_id BIGINT NOT NULL COMMENT '所属学院',
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位'
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8 COMMENT '专业表';

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '学生id',
  `stu_number` varchar(100) NOT NULL COMMENT '学生学号',
  `name` varchar(100) NOT NULL COMMENT '学生姓名',
  `sex` TINYINT DEFAULT 1 COMMENT '学生性别 0 未知 1 男 2 女',
  `profession_id` BIGINT NOT NULL COMMENT '学生专业',
  `depart_id` BIGINT NOT NULL COMMENT '学生学院',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  auth_id BIGINT NOT NULL ,
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  UNIQUE KEY `idx_stu_number` (`stu_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生表';

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '教师ID',
  `worker_id` varchar(100) NOT NULL COMMENT '教师工号',
  `name` varchar(100) NOT NULL COMMENT '教师姓名',
  `sex` TINYINT DEFAULT 1 COMMENT '学生性别 0 未知 1 男 2 女',
  `job_title` varchar(100) NOT NULL COMMENT '教师职称',
  `profession_id` BIGINT NOT NULL COMMENT '专业',
  `depart_id` BIGINT NOT NULL COMMENT '学院',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  auth_id BIGINT NOT NULL ,
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  UNIQUE KEY `idx_worker_id` (`worker_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表';

-- --------
-- Table structure for user_menu
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '权限菜单ID',
  `key_name` varchar(255) NOT NULL COMMENT '菜单key',
  `name` varchar(255) NOT NULL COMMENT '权限菜单名',
  `icon` varchar(255) NOT NULL COMMENT '权限菜单图标',
  `href` varchar(255) NOT NULL COMMENT '权限菜单跳转路径',
  `parent_id` varchar(40) NOT NULL COMMENT '父级菜单ID,顶级菜单为0',
  `role_id` INT NOT NULL COMMENT '菜单所属的角色类型',
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` BIGINT NOT NULL COMMENT '课程id',
  `name` varchar(255) NOT NULL COMMENT '课程名称',
  `teacher_id` BIGINT NOT NULL COMMENT '授课教师id',
  `company_id` BIGINT NOT NULL COMMENT '授课公司id',
  `profession_ids` varchar(255) DEFAULT '' COMMENT '课程所属的专业id,多个专业以;间隔',
  `memo` varchar(255) DEFAULT NULL COMMENT '课程描述',
  `start_time` DATETIME NULL DEFAULT NULL COMMENT '开课时间',
  `end_time` DATETIME NULL DEFAULT NULL COMMENT '结课时间',
  `addition` varchar(255) DEFAULT NULL COMMENT '课程附件在服务器上的url地址',
  `state` tinyint(4) NOT NULL DEFAULT '3' COMMENT '开课状态,0 开放选课中,1 关闭选课,2 开课中,3 已结课',
  create_time DATETIME NOT NULL ,
  update_time DATETIME NOT NULL ,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';

DROP TABLE IF EXISTS course_profession;
CREATE TABLE course_profession(
  id BIGINT NOT NULL PRIMARY KEY ,
  course_id BIGINT NOT NULL ,
  profession_id BIGINT NOT NULL,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  KEY idx_course_pro (course_id, profession_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_selection
-- ----------------------------
DROP TABLE IF EXISTS `course_selection`;
CREATE TABLE `course_selection` (
  `id` BIGINT NOT NULL COMMENT '选课表id',
  `course_id` BIGINT NOT NULL COMMENT '课程id',
  `student_id` BIGINT NOT NULL COMMENT '学生id',
  `create_time` DATETIME NOT NULL COMMENT '选课时间',
  `update_time` DATETIME not NULL COMMENT '修改时间',
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_c_id_s_id` (`course_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生选课表';

-- ----------------------------
-- Table structure for course_task
-- ----------------------------
DROP TABLE IF EXISTS `course_task`;
CREATE TABLE `course_task` (
  `id` BIGINT NOT NULL COMMENT '任务id',
  `course_id` BIGINT NOT NULL COMMENT '任务所属课程的id',
  `name` varchar(255) NOT NULL COMMENT '任务名称',
  `content` varchar(255) NOT NULL COMMENT '任务内容，保存上传的任务内容文件的url地址',
  `start_time` DATETIME not NULL COMMENT '任务开始时间',
  `end_time` DATETIME not NULL  COMMENT '任务结束时间',
  `deadline` DATETIME not NULL  COMMENT '任务成果截止时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '任务描述',
  create_user_id BIGINT NOT NULL COMMENT 'create user auth id',
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程任务表';

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` BIGINT NOT NULL COMMENT '分数id',
  `course_selection_id` BIGINT NOT NULL COMMENT '选课id',
  `student_id` BIGINT NOT NULL COMMENT '学生id',
  `teacher_score` DECIMAL DEFAULT '0' COMMENT '教师评分',
  `company_score` DECIMAL DEFAULT '0' COMMENT '公司评分',
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_cs_s` (`course_selection_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生分数表';


-- ----------------------------
-- Table structure for task_result
-- ----------------------------
DROP TABLE IF EXISTS `task_result`;
CREATE TABLE `task_result` (
  `id` BIGINT NOT NULL COMMENT '任务成果表',
  `task_id` BIGINT NOT NULL COMMENT '任务id',
  `student_id` BIGINT NOT NULL COMMENT '学生id',
  `content` varchar(255) NOT NULL COMMENT '',
  addition_url VARCHAR(255) NOT NULL ,
  `memo` varchar(255) DEFAULT NULL COMMENT '任务成果描述(备注)',
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  del_flag BIT(1) NOT NULL DEFAULT 0 COMMENT '删除标记位',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_task_id_student_id` (`task_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务成果表';
