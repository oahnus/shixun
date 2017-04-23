package top.oahnus.service;

import top.oahnus.dto.CourseTaskDto;
import top.oahnus.entity.CourseTask;

import java.util.List;

/**
 * Created by oahnus on 2017/4/23
 * 17:14.
 */
public interface CourseTaskService {
    List<CourseTask> selectCourseTaskByCourseId(String courseId);
    Integer insertNewCourseTask(CourseTaskDto courseTaskDto);
    Integer updateCourseTask(CourseTaskDto courseTaskDto);
    Integer deleteCourseTaskById(String courseTaskId);
}