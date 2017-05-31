package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.oahnus.entity.Student;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.payload.StudentPayload;
import top.oahnus.repository.StudentRepository;
import top.oahnus.util.StringUtil;

/**
 * Created by oahnus on 2017/5/27
 * 21:41.
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> findByNameLike(String name, Integer page, Integer limit) {
        if (page == null || limit == null || StringUtil.isEmpty(name)) throw new BadRequestParamException("缺失请求参数");
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Student> students = studentRepository.findByNameLike(name, pageable);
        return students;
    }

    public Student getById(Long id) {
        return studentRepository.findOne(id);
    }

    public Page<Student> findByStudentNumLike(String studentNum, Integer page, Integer limit) {
        if (page == null || limit == null || StringUtil.isEmpty(studentNum)) throw new BadRequestParamException("缺失请求参数");
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Student> students = studentRepository.findByStudentNumLike(studentNum, pageable);
        return students;
    }
    public Page<Student> findByDepart(Long departId, Integer page, Integer limit) {
        if (page == null || limit == null || departId == null) throw new BadRequestParamException("缺失请求参数");
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Student> students = studentRepository.findByDepartId(departId, pageable);
        return students;
    }
    public Page<Student> findByProfession(Long professionId, Integer page, Integer limit) {
        if (page == null || limit == null || professionId == null) throw new BadRequestParamException("缺失请求参数");
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Student> students = studentRepository.findByProfessionId(professionId, pageable);
        return students;
    }

    public Page<Student> getStudentByPage(Integer page, Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Student> students = studentRepository.findAll(pageable);
        return students;
    }

    public Student insertStudent(StudentPayload payload) {
        Student student = Student.fromPayload(payload);
        student = studentRepository.save(student);
        return student;
    }

    public Student updateStudent(StudentPayload payload) {
        if (payload.getId() == null) throw new BadRequestParamException("id为空");

        Student student = studentRepository.findOne(payload.getId());

        if (student == null) throw new NotFoundException("数据未找到");

        student = Student.fromPayload(payload);
        student = studentRepository.save(student);
        return student;
    }

    public void deleteStudentById(Long id) {
        if (id == null) throw new BadRequestParamException("id为空");
        studentRepository.delete(id);
    }
}
