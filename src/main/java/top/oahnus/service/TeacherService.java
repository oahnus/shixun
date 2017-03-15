package top.oahnus.service;

import top.oahnus.entity.Teacher;

import java.util.List;

/**
 * Created by oahnus on 2017/3/1.
 */
public interface TeacherService {
    List<Teacher> insertTeachers(List<Teacher> teachers);
    List<Teacher> getTeachersByDepart(String depart, int page, int size);
    List<Teacher> getTeachersByProfession(String profession, int page, int size);
}
