package top.oahnus.service;

import top.oahnus.dto.Page;
import top.oahnus.dto.StudentDto;
import top.oahnus.entity.Student;

import java.util.List;

/**
 * Created by oahnus on 2017/3/28
 * 22:08.
 */
public interface StudentService {
    Page<List<Student>> selectStudentByProfession(String profession, Integer page, Integer limit);
    Page<List<Student>> selectStudentByDepart(String depart, Integer page, Integer limit);
    Page<List<Student>> selectAllStudent(Integer page, Integer limit);
    Student selectStudentById(String studentId);

    List<Student> insertStudents(List<Student> studentList);
    Student insertOneStudent(StudentDto studentDto);

    Integer deleteStudentById(String studentId);
    Student updateStudent(StudentDto studentDto);
}
