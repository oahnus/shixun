DROP DATABASE IF EXISTS shixun;
CREATE DATABASE IF NOT EXISTS shixun;
USE shixun;

-- ------------------------------------------

# 管理员表
DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  username VARCHAR(255) NOT NULL COMMENT '管理员用户名',
  nickname VARCHAR(255) COMMENT '管理员昵称',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '管理员表';

DELIMITER ;;
CREATE TRIGGER before_insert_admin
AFTER INSERT ON admin
FOR EACH ROW
  BEGIN
    # 密码默认为用户名
    INSERT INTO user_auth VALUE (NULL, new.username, md5(new.username), 0);
  END ;;

-- ------------------------------------------

# 公司表
DROP TABLE IF EXISTS company;
CREATE TABLE company (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '公司ID',
  name VARCHAR(100) NOT NULL COMMENT '公司名称',
  contact VARCHAR(100) NOT NULL COMMENT '公司联系人，姓名',
  contact_phone VARCHAR(100) NULL COMMENT '公司联系人手机号',
  address VARCHAR(255) NOT NULL COMMENT '公司地址',
  email VARCHAR(255) NULL COMMENT '找回密码验证邮箱地址',
  PRIMARY KEY (id),
  UNIQUE KEY idx_name (name)
) ENGINE =InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '公司表';

DELIMITER ;;
CREATE TRIGGER before_insert_company
AFTER INSERT ON company
FOR EACH ROW
  BEGIN
    INSERT INTO user_auth VALUE (NULL, new.name, md5(new.name), 1);
  END ;;

-- ---------------------------------------

DROP TABLE IF EXISTS depart;
CREATE TABLE depart (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL COMMENT '学院名称',
  PRIMARY KEY (id)
)ENGINE =InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '学院表';

DROP TABLE IF EXISTS profession;
CREATE TABLE profession (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL COMMENT '专业名称',
  depart_id BIGINT NOT NULL COMMENT '所属学院id',
  PRIMARY KEY (id),
  KEY idx_depart_id (depart_id)
)ENGINE =InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '专业表';

# 教师表
DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '教师ID',
  worker_id VARCHAR(100) NOT NULL COMMENT '教师工号',
  name VARCHAR(100) NOT NULL COMMENT '教师姓名',
  sex ENUM('男', '女', '未知') DEFAULT '未知' NOT NULL COMMENT '性别',
  job_title VARCHAR(100) NOT NULL COMMENT '教师职称',
  profession_id BIGINT NOT NULL COMMENT '专业',
  depart_id BIGINT NOT NULL COMMENT '学院',
  email VARCHAR(255) NULL COMMENT '找回密码验证邮箱地址',
  PRIMARY KEY (id),
  UNIQUE KEY idx_worker_id (worker_id)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '教师表';

DELIMITER ;;
CREATE TRIGGER before_insert_teacher
AFTER INSERT ON teacher
FOR EACH ROW
  BEGIN
    INSERT INTO user_auth VALUE (NULL, new.worker_id, md5(new.worker_id), 2);
  END ;;

-- -----------------------------------------

# 学生表
DROP TABLE IF EXISTS student;
CREATE TABLE student (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '学生id',
  student_num VARCHAR(100) NOT NULL COMMENT '学生学号',
  name VARCHAR(100) NOT NULL COMMENT '学生姓名',
  sex ENUM('男', '女', '未知') DEFAULT '未知' COMMENT '学生性别',
  profession_id BIGINT NOT NULL COMMENT '学生专业',
  depart_id BIGINT NOT NULL COMMENT '学院',
  email VARCHAR(255) NULL COMMENT '找回密码验证邮箱地址',
  PRIMARY KEY (id),
  UNIQUE KEY idx_student_num (student_num)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '学生表';

DELIMITER ;;
CREATE TRIGGER before_insert_student
AFTER INSERT ON student
FOR EACH ROW
  BEGIN
    INSERT INTO user_auth VALUE (NULL, new.student_num, md5(new.student_num), 3);
  END ;;

-- ---------------------------------------------

# 课程表
DROP TABLE IF EXISTS course;
CREATE TABLE course (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '课程id',
  name VARCHAR(255) NOT NULL COMMENT '课程名称',
  teacher_id BIGINT NOT NULL COMMENT '授课教师id',
  company_id BIGINT NOT NULL COMMENT '授课公司id',
  professions VARCHAR(255) DEFAULT '' COMMENT '课程所属的专业,多个专业以;间隔',
  memo VARCHAR(255) NULL COMMENT '课程描述',
  start_time DATETIME NULL COMMENT '开课时间',
  end_time DATETIME NULL COMMENT '结课时间',
  addition VARCHAR(255) NULL COMMENT '课程附件在服务器上的url地址',
  update_time DATETIME NULL COMMENT '课程信息跟新时间',
  PRIMARY KEY (id),
  UNIQUE KEY idx_teacher_company (name, teacher_id, company_id),
  KEY idx_start_time (start_time),
  KEY idx_profession (professions)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '课程表';

-- --------------------------------------------

# 选课表
DROP TABLE IF EXISTS course_selection;
CREATE TABLE course_selection (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '选课表id',
  course_id BIGINT NOT NULL COMMENT '课程id',
  student_id BIGINT NOT NULL COMMENT '学生id',
  create_time DATETIME NULL COMMENT '选课时间',
  edit_time DATETIME NULL COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE KEY idx_c_id_s_id (course_id, student_id)
)ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '学生选课表';

-- -------------------------------------------

# 课程任务表
DROP TABLE IF EXISTS course_task;
CREATE TABLE course_task (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '任务id',
  course_id BIGINT NOT NULL COMMENT '任务所属课程的id',
  name VARCHAR(255) NOT NULL COMMENT '任务名称',
  content VARCHAR(255) NOT NULL COMMENT '任务内容，保存上传的任务内容文件的url地址',
  start_time DATETIME NULL COMMENT '任务开始时间',
  end_time DATETIME NULL COMMENT '任务结束时间',
  deadline DATETIME NULL COMMENT '任务成果截止时间',
  memo VARCHAR(255) NULL COMMENT '任务描述',
  PRIMARY KEY (id),
  KEY idx_course_id_name (course_id, name),
  KEY idx_start_time (start_time),
  KEY idx_end_time (end_time)
)ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '课程任务表';

-- -----------------------------------------------

# 分数表
DROP TABLE IF EXISTS score;
CREATE TABLE score (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '分数id',
  course_selection_id BIGINT NOT NULL COMMENT '选课id',
  student_id BIGINT NOT NULL COMMENT '学生id',
  teacher_score FLOAT(11) DEFAULT 0 COMMENT '教师评分',
  company_score FLOAT(11) DEFAULT 0 COMMENT '公司评分',
  PRIMARY KEY (id),
  UNIQUE KEY idx_course_selection_id_student_id (course_selection_id, student_id)
)ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '学生分数表';

-- ---------------------------------------------


# 任务成果表
DROP TABLE IF EXISTS task_result;
CREATE TABLE task_result (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '任务成果表',
  task_id BIGINT NOT NULL COMMENT '任务id',
  student_id BIGINT NOT NULL COMMENT '学生id',
  content VARCHAR(255) NOT NULL COMMENT '上传的成果文件url',
  memo VARCHAR(255) NULL COMMENT '任务成果描述(备注)',
  create_time DATETIME NULL COMMENT '提交时间',
  PRIMARY KEY (id),
  UNIQUE KEY idx_task_id_student_id (task_id, student_id)
)ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COMMENT '任务成果表';

-- --------------------------------------------

# 用户权限表
DROP TABLE IF EXISTS user_auth;
CREATE TABLE user_auth (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户权限表的ID',
  username VARCHAR(255) NOT NULL COMMENT '用户名，管理员为username,教师为worker_id,公司为name,学生为student_num',
  password VARCHAR(255) NOT NULL COMMENT '密码',
  type INT(1) NOT NULL COMMENT '用户的角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

-- -----------------------------------------
# 17-03-29
ALTER TABLE course ADD COLUMN state TINYINT NOT NULL DEFAULT 3 COMMENT '开课状态,0 开放选课中,1 关闭选课,2 开课中,3 已结课';

-- ----------------------------------------
# 用户菜单表
DROP TABLE IF EXISTS user_menu;
CREATE TABLE user_menu (
  id BIGINT AUTO_INCREMENT NOT NULL COMMENT '权限菜单ID',
  key_name VARCHAR(255) NOT NULL COMMENT '菜单key',
  name VARCHAR(255) NOT NULL COMMENT '权限菜单名',
  icon VARCHAR(255) NOT NULL COMMENT '权限菜单图标',
  href VARCHAR(255) NOT NULL COMMENT '权限菜单跳转路径',
  parent_id BIGINT NOT NULL COMMENT '父级菜单ID,顶级菜单为0',
  auth_type INT(1) NOT NULL COMMENT '菜单所属的角色类型',
  PRIMARY KEY (id),
  KEY idx_parent_id(parent_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ------------------------------------------
# 评价表 双向 学生评教师，教师评学生，学生评公司，公司评学生
DROP TABLE IF EXISTS review;
CREATE TABLE review (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  from_user_username VARCHAR(40) NOT NULL COMMENT '评论人用户名',
  from_user_name VARCHAR(40) NOT NULL COMMENT '评论人名称',
  from_user_type INT(1) NOT NULL COMMENT '评论人角色',
  to_user_username VARCHAR(40) NOT NULL COMMENT '被评论人用户名',
  to_user_name VARCHAR(40) NOT NULL COMMENT '被评论人名称',
  to_user_type INT(1) NOT NULL COMMENT '被评论人角色',
  course_id BIGINT NOT NULL COMMENT '被评论课程id',
  course_name VARCHAR(255) NOT NULL COMMENT '被评论课程名称',
  content VARCHAR(255) NOT NULL COMMENT '内容',
  rate INT(1) NOT NULL COMMENT '评论等级',
  create_time DATETIME NOT NULL COMMENT '',
  PRIMARY KEY (id),
  UNIQUE KEY idx_from_to_course(from_user_username, to_user_username, course_id)
)ENGINE=InnoDB AUTO_INCREMENT= 1 DEFAULT CHARSET=utf8;
