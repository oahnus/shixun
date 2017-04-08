package top.oahnus.service;

import org.apache.ibatis.annotations.Param;
import top.oahnus.dto.CourseDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Course;

import java.util.List;

/**
 * Created by oahnus on 2017/4/3
 * 21:19.
 */
public interface CourseService {
    Page<List<Course>> selectAllCourse(Integer page, Integer limit);
    Page<List<Course>> selectCourseByProfessionsLikeProfession(String profession, Integer page, Integer limit);
    Page<List<Course>> selectCourseByTeacherId(String teacherId, Integer page, Integer limit);
    Page<List<Course>> selectCourseByCompanyId(String companyId, Integer page, Integer limit);

    Course insertNewCourse(CourseDto courseDto);

    Course updateCourse(CourseDto courseDto);

    Integer deleteCourseById(String courseId);
}
