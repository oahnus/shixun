package top.oahnus.service;

import top.oahnus.dto.CourseDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Course;

import java.util.List;

/**
 * Created by oahnus on 2017/4/3
 * 21:19.
 */
public interface CourseService {
    Page<List<Course>> selectAllCourse(String state, Integer page, Integer limit);
    Page<List<Course>> selectCourseByProfessionsLikeProfession(String state, String profession, Integer page, Integer limit);
    Page<List<Course>> selectCourseByTeacherId(String state, String teacherId, Integer page, Integer limit);
    Page<List<Course>> selectCourseByCompanyId(String state, String companyId, Integer page, Integer limit);
    Course selectCourseByCourseId(String courseId);

    Course insertNewCourse(CourseDto courseDto);

    Course updateCourse(CourseDto courseDto);

    Integer deleteCourseById(String courseId);
}
