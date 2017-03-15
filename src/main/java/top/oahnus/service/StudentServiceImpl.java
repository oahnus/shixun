package top.oahnus.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.entity.Student;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.NotFoundException;

import java.util.List;

/**
 * Created by oahnus on 2017/3/2.
 * 21:35
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> insertStudents(List<Student> students) {
        for(Student student: students){
//            studentRepository.save(student);
        }
        return students;
    }

    @Override
    public List<Student> selectStudentByDepart(String depart, int page, int size) {
        if (page < 0 || size < 0) {
            throw new BadRequestParamException("参数错误");
        }
        Pageable pageable = new PageRequest(page, size);
//        Page<Student> students = studentRepository.findByDepart(depart, pageable);
        if (students == null) {
            throw new NotFoundException("数据未找到");
        }
        return students.getContent();
    }

    @Override
    public List<Student> selectStudentByProfession(String profession, int page, int size) {
        if (page < 0 || size < 0) {
            throw new BadRequestParamException("参数错误");
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Student> students = studentRepository.findByProfession(profession, pageable);
        if (students == null) {
            throw new NotFoundException("数据未找到");
        }
        return students.getContent();
    }

    @Override
    @Transactional
    public Student insertStudent(Student student) {
        Student s = studentRepository.findByStudentNum(student.getStudentNum());
        if (s != null) {
            throw new DataExistedException("数据已存在");
        }
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public Student updateStudent(Student student) {
        Student s = studentRepository.findOne(student.getId());
        if(s == null) {
            throw new NotFoundException("数据未找到");
        }
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student s = studentRepository.findOne(studentId);
        if(s == null) {
            throw new NotFoundException("数据未找到");
        }
        studentRepository.delete(studentId);
    }
}
