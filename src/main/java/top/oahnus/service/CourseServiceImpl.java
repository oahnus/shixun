package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.CourseDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Course;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.CourseMapper;
import top.oahnus.util.StringUtil;

import java.util.List;

/**
 * Created by oahnus on 2017/4/3
 * 22:55.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Page<List<Course>> selectAllCourse(Integer page, Integer limit) {
        if (page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        List<Course> courses = courseMapper.selectAllCourse((page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectCountCourseByCondition(null, null, null);
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    public Page<List<Course>> selectCourseByProfessionsLikeProfession(String profession, Integer page, Integer limit) {
        if (StringUtil.isEmpty(profession) || page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        List<Course> courses = courseMapper.selectCourseByProfessionsLikeProfession(profession, (page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectCountCourseByCondition(null, null, profession);
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    public Page<List<Course>> selectCourseByTeacherId(String teacherId, Integer page, Integer limit) {
        if (StringUtil.isEmpty(teacherId) || page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        List<Course> courses = courseMapper.selectCourseByTeacherId(teacherId, (page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectCountCourseByCondition(teacherId, null, null);
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    public Page<List<Course>> selectCourseByCompanyId(String companyId, Integer page, Integer limit) {
        if (StringUtil.isEmpty(companyId) || page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        List<Course> courses = courseMapper.selectCourseByCompanyId(companyId, (page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectCountCourseByCondition(null, companyId, null);
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    public Course insertNewCourse(CourseDto courseDto) {
        if (courseDto == null) throw new BadRequestParamException("请求参数错误");
        Course course = new Course(courseDto);
        Integer count = courseMapper.insertNewCourse(course);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据失败");
        } else if (count == 0){
            throw new DataExistedException("插入失败,数据已存在");
        } else {
            course = courseMapper.selectCourseByNameAndTeacherIdAndCompanyId(courseDto.getName(),courseDto.getTeacherId(),courseDto.getCompanyId());
            return course;
        }
    }

    @Override
    public Integer deleteCourseById(String courseId) {
        if (courseId == null) throw new BadRequestParamException("请求参数错误");
        Integer count = courseMapper.deleteCourseById(courseId);
        if (count < 0) throw new SQLExecuteFailedExceeption("删除失败");
        return count;
    }
}
