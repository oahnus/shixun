INSERT INTO admin (id, username, nickname) VALUE (NULL, 'admin', 'admin');

# 插入测试数据
INSERT INTO student (id, student_num, name, sex, profession, depart)
VALUES
  (NULL, '1341901120', '孙浩', '男', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901121', '凌夏明', '男', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901122', '朱清宇', '男', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901123', '金鹏飞', '男', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901124', '屈冉', '男', '计算机科学与技术', '计算机科学与工程'),
  (NULL, '1341901125', '阮仕宏', '男', '计算机科学与技术', '计算机科学与工程');

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

# 用户菜单表
# 在初始化时，读取用户菜单表到redis
# auth_type 0 管理员;1 公司;2 教师;3 学生;
INSERT INTO user_menu (id, key_name, name, icon, href, parent_id, auth_type)
VALUES
  (1, 'studentCourseManagement', '课程管理', 'book', '', 0, 3),
  (2, 'selectCourse', '选择课程', '', 'home/selectCourse', 1, 3),
  (3, 'cancelCourse', '查看课程', '', 'home/cancelCourse', 1, 3),
  (4, 'accomplishCourse', '已修课程', '', 'home/accomplishCourse', 1, 3),
  (5, 'studyCourse', '学习课程', '', 'home/studyCourse', 1, 3),

  (6, 'teacherCourseManagement', '课程管理', 'book', '', 0, 2),
  (7, 'selectCourseInfo', '选课信息', '', 'home/selectCourseInfo', 6, 2),
  (8, 'teacherTaughtCourse', '已授课程', '', 'home/teacherTaughtCourse', 6, 2),
  (9, 'teacherTeachingCourse', '在授课程', '', 'home/teacherTeachingCourse', 6, 2),
  (10, 'teacherCountManagement', '统计管理', 'area-chart', '', 0, 2),
  (11, 'teacherCountTaughtCourse', '统计授课', '', 'home/teacherCountTaughtCourse', 10, 2),

  (12, 'companyCourseManagement', '课程管理', 'book', '', 0, 1),
  (13, 'companyTaughtCourse', '已授课程', '', 'home/companyTaughtCourse', 12, 1),
  (14, 'companyTeachingCourse', '在授课程', '', 'home/companyTeachingCourse', 12, 1),
  (15, 'companyCountManagement', '统计管理', 'area-chart', '', 0, 1),
  (16, 'companyCountTaughtCourse', '统计授课', '', 'home/companyCountTaughtCourse', 15, 1),

  (17, 'infoManagement', '信息管理', 'list', '', 0, 0),
  (18, 'courseManagement', '课程管理', '', 'home/courseManagement', 17, 0),
  (19, 'courseSelectionManagement', '选课管理', '', 'home/courseSelectionManagement', 17, 0),
  (20, 'studentManagement', '学生管理', '', 'home/studentManagement', 17, 0),
  (21, 'teacherManagement', '教师管理', '', 'home/teacherManagement', 17, 0),
  (22, 'companyManagement', '企业管理', '', 'home/companyManagement', 17, 0),
  (23, 'countManagement', '统计管理', 'area-chart', 'home/countManagement', 0, 0),
  (24, 'countCourseSelection', '统计选课', '', 'home/countCourseSelection', 23, 0),
  (25, 'countTaughtCourse', '统计授课', '', 'home/countTaughtCourse', 23, 0),
  (26, 'countScore', '统计分数', '', 'home/countScore', 23, 0);
