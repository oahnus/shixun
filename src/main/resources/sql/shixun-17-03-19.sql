DROP DATABASE shixun_17_02_26;
CREATE DATABASE IF NOT EXISTS shixun_17_02_26;
USE shixun_17_02_26;

-- ------------------------------------------

# 管理员表
DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
  id VARCHAR(255) NOT NULL COMMENT '管理员ID',
  username VARCHAR(255) NOT NULL COMMENT '管理员用户名',
  nickname VARCHAR(255) COMMENT '管理员昵称',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '管理员表';

# 在插入表之前生成唯一的id
DELIMITER ;;
CREATE TRIGGER before_insert_admin
BEFORE INSERT ON admin
FOR EACH ROW
  BEGIN
    # 管理员密码默认为administrator
    INSERT INTO user_auth VALUE (NULL, new.username, md5('administrator'), 0);
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- ------------------------------------------

# 公司表
DROP TABLE IF EXISTS company;
CREATE TABLE company (
  id VARCHAR(255) NOT NULL COMMENT '公司ID',
  name VARCHAR(100) NOT NULL COMMENT '公司名称',
  contact VARCHAR(100) NOT NULL COMMENT '公司联系人，姓名',
  contact_phone VARCHAR(100) NULL COMMENT '公司联系人手机号',
  address VARCHAR(255) NOT NULL COMMENT '公司地址',
  email VARCHAR(255) NULL COMMENT '找回密码验证邮箱地址',
  PRIMARY KEY (id),
  UNIQUE KEY idx_name (name)
) ENGINE =InnoDB DEFAULT CHARSET = utf8 COMMENT '公司表';

DELIMITER ;;
CREATE TRIGGER before_insert_company
BEFORE INSERT ON company
FOR EACH ROW
  BEGIN
    # 公司密码默认为123456
    INSERT INTO user_auth VALUE (NULL, new.name, md5('123456'), 1);
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- ---------------------------------------

# 教师表
DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher (
  id VARCHAR(255) NOT NULL COMMENT '教师ID',
  worker_id VARCHAR(100) NOT NULL COMMENT '教师工号',
  name VARCHAR(100) NOT NULL COMMENT '教师姓名',
  sex ENUM('男', '女', '未知') DEFAULT '未知' NOT NULL COMMENT '性别',
  job_title VARCHAR(100) NOT NULL COMMENT '教师职称',
  profession VARCHAR(100) NOT NULL COMMENT '专业',
  depart VARCHAR(100) NOT NULL COMMENT '学院',
  email VARCHAR(255) NULL COMMENT '找回密码验证邮箱地址',
  PRIMARY KEY (id),
  UNIQUE KEY idx_worker_id (worker_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '教师表';

DELIMITER ;;
CREATE TRIGGER before_insert_teacher
BEFORE INSERT ON teacher
FOR EACH ROW
  BEGIN
    # 教师密码默认为worker_id
    INSERT INTO user_auth VALUE (NULL, new.worker_id, md5(new.worker_id), 2);
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- -----------------------------------------

# 学生表
DROP TABLE IF EXISTS student;
CREATE TABLE student (
  id VARCHAR(255) NOT NULL COMMENT '学生id',
  student_num VARCHAR(100) NOT NULL COMMENT '学生学号',
  name VARCHAR(100) NOT NULL COMMENT '学生姓名',
  sex ENUM('男', '女', '未知') DEFAULT '未知' COMMENT '学生性别',
  profession VARCHAR(100) NOT NULL COMMENT '学生专业',
  depart VARCHAR(100) NOT NULL COMMENT '学生学院',
  email VARCHAR(255) NULL COMMENT '找回密码验证邮箱地址',
  PRIMARY KEY (id),
  UNIQUE KEY idx_student_num (student_num)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '学生表';

DELIMITER ;;
CREATE TRIGGER before_insert_student
BEFORE INSERT ON student
FOR EACH ROW
  BEGIN
    # 学生密码默认为student_num
    INSERT INTO user_auth VALUE (NULL, new.student_num, md5(new.student_num), 3);
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- ---------------------------------------------

# 课程表
DROP TABLE IF EXISTS course;
CREATE TABLE course (
  id VARCHAR(255) NOT NULL COMMENT '课程id',
  name VARCHAR(255) NOT NULL COMMENT '课程名称',
  teacher_id VARCHAR(255) NOT NULL COMMENT '授课教师id',
  company_id VARCHAR(255) NOT NULL COMMENT '授课公司id',
  professions VARCHAR(255) DEFAULT '' COMMENT '课程所属的专业,多个专业以;间隔',
  memo VARCHAR(255) NULL COMMENT '课程描述',
  start_time TIMESTAMP DEFAULT current_timestamp COMMENT '开课时间',
  end_time TIMESTAMP NULL COMMENT '结课时间',
  addition VARCHAR(255) NULL COMMENT '课程附件在服务器上的url地址',
  update_time TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '课程信息跟新时间',
  PRIMARY KEY (id),
  UNIQUE KEY idx_teacher_company (name, teacher_id, company_id),
  KEY idx_start_time (start_time),
  KEY idx_profession (professions)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '课程表';

DELIMITER ;;
CREATE TRIGGER before_insert_course
  BEFORE INSERT ON course
  FOR EACH ROW
  BEGIN
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- --------------------------------------------

# 选课表
DROP TABLE IF EXISTS course_selection;
CREATE TABLE course_selection (
  id VARCHAR(255) NOT NULL COMMENT '选课表id',
  course_id VARCHAR(255) NOT NULL COMMENT '课程id',
  student_id VARCHAR(255) NOT NULL COMMENT '学生id',
  course_update_time TIMESTAMP NOT NULL COMMENT '课程更新时间,用于区分不同学期的课程',
  create_time TIMESTAMP DEFAULT current_timestamp COMMENT '选课时间',
  edit_time TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE KEY idx_c_id_s_id_c_update (course_id, student_id, course_update_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '学生选课表';

DELIMITER ;;
CREATE TRIGGER before_insert_course_selection
BEFORE INSERT ON course_selection
FOR EACH ROW
  BEGIN
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- -------------------------------------------

# 课程任务表
DROP TABLE IF EXISTS course_task;
CREATE TABLE course_task (
  id VARCHAR(255) NOT NULL COMMENT '任务id',
  course_id VARCHAR(255) NOT NULL COMMENT '任务所属课程的id',
  name VARCHAR(255) NOT NULL COMMENT '任务名称',
  content VARCHAR(255) NOT NULL COMMENT '任务内容，保存上传的任务内容文件的url地址',
  start_time TIMESTAMP DEFAULT current_timestamp COMMENT '任务开始时间',
  end_time TIMESTAMP DEFAULT current_timestamp COMMENT '任务结束时间',
  memo VARCHAR(255) NULL COMMENT '任务描述',
  PRIMARY KEY (id),
  KEY idx_course_id_name (course_id, name),
  KEY idx_start_time (start_time),
  KEY idx_end_time (end_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '课程任务表';

DELIMITER ;;
CREATE TRIGGER before_insert_course_task
BEFORE INSERT ON course_task
FOR EACH ROW
  BEGIN
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- -----------------------------------------------

# 任务成果表
DROP TABLE IF EXISTS task_result;
CREATE TABLE task_result (
  id VARCHAR(255) NOT NULL COMMENT '任务成果表',
  task_id VARCHAR(255) NOT NULL COMMENT '任务id',
  student_id VARCHAR(255) NOT NULL COMMENT '学生id',
  content VARCHAR(255) NOT NULL COMMENT '上传的成果文件url',
  memo VARCHAR(255) NULL COMMENT '任务成果描述(备注)',
  PRIMARY KEY (id),
  UNIQUE KEY idx_task_id_student_id (task_id, student_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '任务成果表';

DELIMITER ;;
CREATE TRIGGER before_insert_task_result
BEFORE INSERT ON task_result
FOR EACH ROW
  BEGIN
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- --------------------------------------------

# 分数表
DROP TABLE IF EXISTS score;
CREATE TABLE score (
  id VARCHAR(255) NOT NULL COMMENT '分数id',
  course_id VARCHAR(255) NOT NULL COMMENT '课程id',
  student_id VARCHAR(255) NOT NULL COMMENT '学生id',
  teacher_score FLOAT(11) DEFAULT 0 COMMENT '教师评分',
  company_score FLOAT(11) DEFAULT 0 COMMENT '公司评分',
  PRIMARY KEY (id),
  UNIQUE KEY idx_course_id_student_id (course_id, student_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '学生分数表';

DELIMITER ;;
CREATE TRIGGER before_insert_score
BEFORE INSERT ON score
FOR EACH ROW
  BEGIN
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

-- ---------------------------------------------

# 用户权限表
DROP TABLE IF EXISTS user_auth;
CREATE TABLE user_auth (
  id VARCHAR(255) NOT NULL COMMENT '用户权限表的ID',
  username varchar(255) NOT NULL COMMENT '用户名，管理员为username,教师为worker_id,公司为name,学生为student_num',
  password varchar(255) NOT NULL COMMENT '密码',
  state int(255) NOT NULL COMMENT '用户的角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELIMITER ;;
CREATE TRIGGER before_insert_user_auth
BEFORE INSERT ON user_auth
FOR EACH ROW
  BEGIN
    SET new.id = REPLACE(uuid(), '-','');
  END ;;

INSERT INTO admin (id, username, nickname) VALUE (NULL, 'admin', 'admin');

# 插入测试数据
INSERT INTO student (id, student_num, name, profession, depart)
VALUES
  (NULL, '1341901120', '孙浩', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901121', '凌夏明', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901122', '朱清宇', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901123', '金鹏飞', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901124', '屈冉', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901125', '阮仕宏', '计算机科学与技术', '计算机科学与工程');

INSERT INTO company (id, name, contact, contact_phone, address)
VALUES
  (NULL, '测试企业一', '测试联系人一', '测试电话一', '测试地址一'),
  (NULL, '测试企业二', '测试联系人二', '测试电话二', '测试地址二'),
  (NULL, '测试企业三', '测试联系人三', '测试电话三', '测试地址三'),
  (NULL, '测试企业四', '测试联系人四', '测试电话四', '测试地址四');

INSERT INTO teacher (id, worker_id, name, sex, job_title, profession, depart)
VALUES
  (NULL, '10012', '张三', '男', '教师', '通信工程', '计算机科学与工程'),
  (NULL, '10011', '王五', '男', '教授', '通信工程', '计算机科学与工程'),
  (NULL, '10013', '李翠华', '女', '助理教师', '经济管理', '经济管理'),
  (NULL, '10014', '李四', '男', '教师', '应用物理', '数理'),
  (NULL, '10015', '找刘', '女', '教师', '计算机', '计算机科学与工程');

# 17-03-29
ALTER TABLE course ADD COLUMN state TINYINT NOT NULL COMMENT '开课状态,0 开放选课中,1 关闭选课,2 开课中,3 已结课';