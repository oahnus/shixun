package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.CourseTaskDto;
import top.oahnus.entity.CourseTask;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.CourseTaskMapper;
import top.oahnus.util.StringUtil;

import java.util.List;

/**
 * Created by oahnus on 2017/4/23
 * 22:17.
 */
@Service
public class CourseTaskService {
    @Autowired
    private CourseTaskMapper courseTaskMapper;

    public List<CourseTask> selectCourseTaskByCourseId(String courseId) {
        if (StringUtil.isEmpty(courseId)) {
            throw new BadRequestParamException("courseId不能为空");
        }
        return courseTaskMapper.selectCourseTaskByCourseId(courseId);
    }

    public Integer insertNewCourseTask(CourseTaskDto courseTaskDto) {
        CourseTask courseTask = new CourseTask(courseTaskDto);
        Integer count = courseTaskMapper.insertNewCourseTask(courseTask);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据失败");
        } else {
            return count;
        }
    }

    public Integer updateCourseTask(CourseTaskDto courseTaskDto) {
        CourseTask courseTask = new CourseTask(courseTaskDto);
        if (courseTask.getId() == null) {
            throw new BadRequestParamException("id不能为空");
        }
        Integer count = courseTaskMapper.updateCourseTask(courseTask);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("更新数据失败");
        } else {
            return count;
        }
    }

    public Integer deleteCourseTaskById(String courseTaskId) {
        Integer count = courseTaskMapper.deleteCourseTask(courseTaskId);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("删除数据失败");
        } else if (count == 0) {
            throw new NotFoundException("数据未找到");
        } else {
            return count;
        }
    }
}
