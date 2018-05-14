package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.oahnus.common.config.RedisDao;
import top.oahnus.common.payload.CourseForm;
import top.oahnus.domain.Course;
import top.oahnus.enums.CourseState;
import top.oahnus.exception.NotFoundException;
import top.oahnus.repository.CourseRepo;

import java.util.Date;
import java.util.List;

/**
 * Created by oahnus on 2017/4/3
 * 22:55.
 */
@Service
public class CourseService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private CourseRepo courseRepo;

    public void switchState(CourseForm form) {
        List<Long> courseIds = form.getCourseIds();
        CourseState state = form.getState();

        Integer rowCount = courseRepo.switchStateWithCourseIdIn(courseIds, state);
    }

    public void save(Course course) {
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        courseRepo.save(course);
    }

    public void update(Course course) {
        course.setUpdateTime(new Date());
        courseRepo.save(course);
    }

    public void deleteById(Long courseId) {
        Course course = courseRepo.findOne(courseId);
        if (course == null) {
            throw new NotFoundException("");
        }
        course.setDelFlag(false);
    }
}
