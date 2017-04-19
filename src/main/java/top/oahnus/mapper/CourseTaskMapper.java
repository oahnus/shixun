package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.CourseTask;

import java.util.List;

/**
 * Created by oahnus on 2017/4/19
 * 16:09.
 */
@Mapper
public interface CourseTaskMapper extends BaseMapper {
    List<CourseTask> selectCourseTaskByCourseId(@Param("courseId")String courseId);

    Integer insertNewCourseTask(CourseTask courseTask);
    Integer updateCourseTask(CourseTask courseTask);
    Integer deleteCourseTask(@Param("courseTaskId")String courseTaskId);
}
