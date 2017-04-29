package top.oahnus.service;

import top.oahnus.dto.CourseDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Course;
import top.oahnus.enums.CourseState;

import java.util.List;

/**
 * Created by oahnus on 2017/4/3
 * 21:19.
 */
public interface CourseService {
    Page<List<Course>> selectAllCourse(CourseState state, Integer page, Integer limit);
    Page<List<Course>> selectCourseByProfessionsLikeProfession(CourseState state, String profession, Integer page, Integer limit);
    Page<List<Course>> selectCourseByTeacherId(CourseState state, String teacherId, Integer page, Integer limit);
    Page<List<Course>> selectCourseByCompanyId(CourseState state, String companyId, Integer page, Integer limit);
    Page<List<Course>> selectCourseByCourseNameLike(CourseState state, String courseName, Integer page, Integer limit);
    Course selectCourseByCourseId(String courseId);

    Course insertNewCourse(CourseDto courseDto);

    Course updateCourse(CourseDto courseDto);
    Integer changeCourseState(String profession, String state);

    Integer deleteCourseById(String courseId);
}
