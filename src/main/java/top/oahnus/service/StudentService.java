package top.oahnus.service;

import top.oahnus.entity.Student;

import java.util.List;

/**
 * Created by oahnus on 2017/3/2.
 * 21:35
 */
public interface StudentService {
    List<Student> insertStudents(List<Student> students);
    List<Student> selectStudentByDepart(String depart, int page, int size);
    List<Student> selectStudentByProfession(String profession, int page, int size);
    Student insertStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(Long studentId);
}
