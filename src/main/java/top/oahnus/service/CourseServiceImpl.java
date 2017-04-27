package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.dto.CourseDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Course;
import top.oahnus.enums.CourseState;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.CourseMapper;
import top.oahnus.util.StringUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by oahnus on 2017/4/3
 * 22:55.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "coursecache", keyGenerator = "myKeyGenerator")
    public Page<List<Course>> selectAllCourse(String state, Integer page, Integer limit) {
        System.out.println("无缓存");
        if (page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        Integer courseState = null;
        if (state != null) {
            try {
                courseState = CourseState.valueOf(state).ordinal();
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new BadRequestParamException("非法课程状态");
            }
        }

        List<Course> courses = courseMapper.selectAllCourse(courseState, (page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectRecordCount(new HashMap<String,String>(){{put("state", String.valueOf(CourseState.valueOf(state).ordinal()));}});
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    @Cacheable(value = "coursecache", keyGenerator = "myKeyGenerator")
    public Page<List<Course>> selectCourseByProfessionsLikeProfession(String state, String profession, Integer page, Integer limit) {
        if (StringUtil.isEmpty(profession) || page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        Integer courseState = null;
        if (state != null) {
            try {
                courseState = CourseState.valueOf(state).ordinal();
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new BadRequestParamException("非法课程状态");
            }
        }

        List<Course> courses = courseMapper.selectCourseByProfessionsLikeProfession(courseState, profession, (page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectRecordCount(
                new HashMap<String, String>(){{
                    put("profession", profession);
                    put("state", String.valueOf(CourseState.valueOf(state).ordinal()));
                }});
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    @Cacheable(value = "coursecache", keyGenerator = "myKeyGenerator")
    public Page<List<Course>> selectCourseByTeacherId(String state, String teacherId, Integer page, Integer limit) {
        if (StringUtil.isEmpty(teacherId) || page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        Integer courseState = null;
        if (state != null) {
            try {
                courseState = CourseState.valueOf(state).ordinal();
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new BadRequestParamException("非法课程状态");
            }
        }

        List<Course> courses = courseMapper.selectCourseByTeacherId(courseState, teacherId, (page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectRecordCount(
                new HashMap<String, String>(){{
                    put("teacherId", teacherId);
                    put("state", String.valueOf(CourseState.valueOf(state).ordinal()));
                }});
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    @Cacheable(value = "coursecache", keyGenerator = "myKeyGenerator")
    public Page<List<Course>> selectCourseByCompanyId(String state, String companyId, Integer page, Integer limit) {
        if (StringUtil.isEmpty(companyId) || page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        Integer courseState = null;
        if (state != null) {
            try {
                courseState = CourseState.valueOf(state).ordinal();
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new BadRequestParamException("非法课程状态");
            }
        }

        List<Course> courses = courseMapper.selectCourseByCompanyId(courseState, companyId, (page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectRecordCount(
                new HashMap<String, String>(){{
                    put("companyId", companyId);
                    put("state", String.valueOf(CourseState.valueOf(state).ordinal()));
                }});
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    @Cacheable(value = "coursecache", keyGenerator = "myKeyGenerator")
    public Page<List<Course>> selectCourseByCourseNameLike(String state, String courseName, Integer page, Integer limit) {
        if (StringUtil.isEmpty(courseName) || page == null || limit == null) throw new BadRequestParamException("请求参数错误");
        CourseState courseState;
        try {
            courseState = CourseState.valueOf(state);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new BadRequestParamException("非法课程状态");
        }

        List<Course> courses = courseMapper.selectCourseByNameLikeCourseName(courseState.ordinal(), courseName, (page - 1) * limit, limit);
        Integer totalRecord = courseMapper.selectRecordCount(
                new HashMap<String, String>(){{
                    put("courseName", courseName);
                    put("state", String.valueOf(courseState.ordinal()));
                }});
        return new Page<>(courses, totalRecord, page, limit);
    }

    @Override
    public Course selectCourseByCourseId(String courseId) {
        if (StringUtil.isEmpty(courseId)) {
            throw new BadRequestParamException("请求参数错误");
        }
        Course course = courseMapper.selectCourseById(courseId);
        if (course == null) {
            throw new NotFoundException("数据未找到");
        }
        return course;
    }

    //todo 判断是否重复插入数据
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
    @CachePut(value = "coursecache")
    public Course updateCourse(CourseDto courseDto) {
        if (courseDto == null) throw new BadRequestParamException("请求参数错误");
        Course course = new Course(courseDto);
        if (course.getId() == null) {
            throw new BadRequestParamException("id不能为空");
        }
        // todo 如果课程已结课，不允许修改课程信息。
        Integer count = courseMapper.updateCourse(course);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("更新数据失败");
        } else if (count == 0) {
            throw new NotFoundException("数据不存在");
        } else {
            return course;
        }
    }

    @Override
    @Transactional
    public Integer changeCourseState(String profession, String state) {
        CourseState courseState;
        try {
            courseState = CourseState.valueOf(state);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new BadRequestParamException("非法课程状态");
        }
        // 如果将课程状态改为关闭选课,则将课程状态改为开课状态
        if (courseState.equals(CourseState.OFF_SELECTED)) {
            Integer count = courseMapper.changeCourseState(profession, CourseState.COURSE_START.ordinal());
            if (count < 0) {
                throw new SQLExecuteFailedExceeption("更新课程状态失败");
            }
            return count;
        } else if(courseState.equals(CourseState.ON_SELECTED)) {
            Integer count = courseMapper.changeCourseState(profession, CourseState.COURSE_START.ordinal());
            if (count < 0) {
                throw new SQLExecuteFailedExceeption("更新课程状态失败");
            }
            return count;
        } else {
            throw new BadRequestParamException("无法修改课程状态");
        }
    }

    @Override
    public Integer deleteCourseById(String courseId) {
        if (courseId == null) throw new BadRequestParamException("请求参数错误");
        Integer count = courseMapper.deleteCourseById(courseId);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("删除失败");
        }
        else if (count == 0) {
            throw new NotFoundException("数据为找到");
        }
        return count;
    }
}
