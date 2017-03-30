package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.TeacherDto;
import top.oahnus.entity.Teacher;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.ReadDataFailedException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.TeacherMapper;
import top.oahnus.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2017/3/28
 * 18:37.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> selectTeacherByProfession(String profession, Integer page, Integer limit) {
        if(page == null || limit == null || StringUtil.isEmpty(profession)) {
            throw new BadRequestParamException("请求参数错误");
        }
        return teacherMapper.selectTeacherByProfession(profession, (page-1)*limit, limit);
    }

    @Override
    public List<Teacher> selectTeacherByDepart(String depart, Integer page, Integer limit) {
        if(page == null || limit == null || StringUtil.isEmpty(depart)) {
            throw new BadRequestParamException("请求参数错误");
        }
        return teacherMapper.selectTeacherByDepart(depart, (page-1)*limit, limit);
    }

    @Override
    public List<Teacher> insertTeachers(List<Teacher> teachers) {
        if (teachers == null) throw new ReadDataFailedException("读取Excel文件失败");

        List<Teacher> teacherList = new ArrayList<>();

        Integer count = teacherMapper.insertTeachers(teachers);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据库失败");
        } else {
            teachers.forEach(teacher -> {
                teacherList.add(teacherMapper.selectTeacherByWorkerId(teacher.getWorkerId()));
            });
        }
        return teacherList;
    }

    @Override
    public Teacher insertOneTeacher(TeacherDto teacherDto) {
        if (teacherDto == null) throw new BadRequestParamException("请求参数错误");

        Teacher teacher = new Teacher(teacherDto);
        Integer count = teacherMapper.insertOneTeacher(teacher);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据库失败");
        } else if (count == 0) {
            throw new DataExistedException("数据已存在");
        } else {
            teacher = teacherMapper.selectTeacherByWorkerId(teacher.getWorkerId());
            return teacher;
        }
    }

    @Override
    public Integer deleteTeacherById(String teacherId) {
        if (StringUtil.isEmpty(teacherId)) throw new BadRequestParamException("请求参数错误");

        Integer count = teacherMapper.deleteTeacherById(teacherId);
        if (count > 0) {
            return count;
        } else {
            throw new SQLExecuteFailedExceeption("删除数据库失败");
        }
    }

    @Override
    public Teacher updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher(teacherDto);
        Integer count = teacherMapper.updateTeacher(teacher);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("更新数据库失败");
        } else {
            return teacher;
        }
    }
}
