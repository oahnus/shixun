package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.oahnus.entity.Teacher;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.payload.TeacherPayload;
import top.oahnus.repository.TeacherRepository;
import top.oahnus.util.StringUtil;

/**
 * Created by oahnus on 2017/5/27
 * 17:41.
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Page<Teacher> getTeacherByPage(Integer page, Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        return teacherRepository.findAll(pageable);
    }

    public Page<Teacher> getTeacherByDepart(Long depart, Integer page, Integer limit) {
        if (depart == null) throw new BadRequestParamException("学院为空");
        Pageable pageable = new PageRequest(page - 1, limit);
        return teacherRepository.findByDepartId(depart, pageable);
    }

    public Page<Teacher> getTeacherByProfession(Long profession, Integer page, Integer limit) {
        if (profession == null) throw new BadRequestParamException("专业为空");
        Pageable pageable = new PageRequest(page - 1, limit);
        return teacherRepository.findByProfessionId(profession, pageable);
    }

    public Page<Teacher> getTeacherByWorkerIdLike(String workerId, Integer page, Integer limit) {
        if (StringUtil.isEmpty(workerId)) throw new BadRequestParamException("教师工号为空");
        Pageable pageable = new PageRequest(page - 1, limit);
        workerId = "%" + workerId + "%";
        return teacherRepository.findByWorkerIdLike(workerId, pageable);
    }

    public Page<Teacher> getTeacherByNameLike(String name, Integer page, Integer limit) {
        if (StringUtil.isEmpty(name)) throw new BadRequestParamException("教师姓名为空");
        Pageable pageable = new PageRequest(page - 1, limit);
        name = "%" + name + "%";
        return teacherRepository.findByNameLike(name, pageable);
    }

    public Teacher insertTeacher(TeacherPayload payload) {
        Teacher teacher = Teacher.fromPayload(payload);
        teacher = teacherRepository.save(teacher);
        return teacher;
    }

    public Teacher updateTeacher(TeacherPayload payload) {
        Teacher teacher = Teacher.fromPayload(payload);
        teacher = teacherRepository.save(teacher);
        return teacher;
    }

    public void deleteTeacherById(Long teacherId) {
        if (teacherId == null) throw new BadRequestParamException("Id为空");
        teacherRepository.delete(teacherId);
    }
}
