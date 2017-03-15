package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.entity.Teacher;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.repository.TeacherRepository;

import java.util.List;

/**
 * Created by oahnus on 2017/3/1.
 * 23:23
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> insertTeachers(List<Teacher> teachers) {
        for(Teacher teacher: teachers){
            teacherRepository.save(teacher);
        }
        return teachers;
    }

    @Override
    public List<Teacher> getTeachersByProfession(String profession, int page, int size) {
        if (page < 0 || size < 0) {
            throw new BadRequestParamException("参数错误");
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Teacher> teachers = teacherRepository.findByProfession(profession, pageable);
        if (teachers == null) {
            throw new NotFoundException("数据未找到");
        }
        return teachers.getContent();
    }

    @Override
    public List<Teacher> getTeachersByDepart(String depart, int page, int size) {
        if (page < 0 || size < 0) {
            throw new BadRequestParamException("参数错误");
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Teacher> teachers = teacherRepository.findByDepart(depart, pageable);
        if (teachers == null) {
            throw new NotFoundException("数据未找到");
        }
        return teachers.getContent();
    }
}
