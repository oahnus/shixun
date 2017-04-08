package top.oahnus.service;

import top.oahnus.dto.Page;
import top.oahnus.dto.TeacherDto;
import top.oahnus.entity.Teacher;

import java.util.List;

/**
 * Created by oahnus on 2017/3/28
 * 18:32.
 */
public interface TeacherService {
    Page<List<Teacher>> selectTeacherByProfession(String profession, Integer page, Integer limit);
    Page<List<Teacher>> selectTeacherByDepart(String depart, Integer page, Integer limit);
    Page<List<Teacher>> selectAllTeacher(Integer page, Integer limit);

    List<Teacher> insertTeachers(List<Teacher> teacherList);
    Teacher insertOneTeacher(TeacherDto teacherDto);
    Integer deleteTeacherById(String teacherId);
    Teacher updateTeacher(TeacherDto teacherDto);
}
