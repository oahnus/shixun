package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.Page;
import top.oahnus.dto.TeacherDto;
import top.oahnus.entity.Teacher;
import top.oahnus.exception.*;
import top.oahnus.mapper.TeacherMapper;
import top.oahnus.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
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
    public Page<List<Teacher>> selectTeacherByProfession(String profession, Integer page, Integer limit) {
        if(page == null || limit == null || StringUtil.isEmpty(profession)) {
            throw new BadRequestParamException("请求参数错误");
        }
        List<Teacher> teachers = teacherMapper.selectTeacherByProfession(profession, (page-1)*limit, limit);
        Integer totalRecord = teacherMapper.selectRecordCount(new HashMap<String, String>(){{put("profession", profession);}});
        return new Page<>(teachers, totalRecord, page, limit);
    }

    @Override
    public Page<List<Teacher>> selectTeacherByDepart(String depart, Integer page, Integer limit) {
        if(page == null || limit == null || StringUtil.isEmpty(depart)) {
            throw new BadRequestParamException("请求参数错误");
        }
        List<Teacher> teachers = teacherMapper.selectTeacherByDepart(depart, (page-1)*limit, limit);
        Integer totalRecord = teacherMapper.selectRecordCount(new HashMap<String, String>(){{put("depart", depart);}});
        return new Page<>(teachers, totalRecord, page, limit);
    }

    @Override
    public Page<List<Teacher>> selectAllTeacher(Integer page, Integer limit) {
        if(page == null || limit == null) {
            throw new BadRequestParamException("请求参数错误");
        }
        List<Teacher> teachers = teacherMapper.selectAllTeacher((page-1)*limit, limit);
        Integer totalRecord = teacherMapper.selectRecordCount(null);
        return new Page<>(teachers, totalRecord, page, limit);
    }

    @Override
    public Teacher selectTeacherById(String teacherId) {
        if (StringUtil.isEmpty(teacherId)) {
            throw new BadRequestParamException("请求参数错误");
        }
        Teacher teacher = teacherMapper.selectTeacherById(teacherId);
        if (teacher == null) {
            throw new NotFoundException("数据未找到");
        }
        return teacher;
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
        if (teacher.getId() == null) {
            throw new BadRequestParamException("id不能为空");
        }
        Integer count = teacherMapper.updateTeacher(teacher);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("更新数据库失败");
        } else if (count == 0) {
            throw new NotFoundException("数据为找到");
        } else {
            return teacher;
        }
    }
}
