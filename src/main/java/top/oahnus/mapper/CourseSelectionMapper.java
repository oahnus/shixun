package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.CourseSelection;

import java.util.List;

/**
 * Created by oahnus on 2017/4/8
 * 13:13.
 */
@Mapper
public interface CourseSelectionMapper extends BaseMapper {
    List<CourseSelection> selectCourseSelectionByStudentId(@Param("studentId")String studentId,
                                                           @Param("offset")Integer offset,
                                                           @Param("limit")Integer limit);
    List<CourseSelection> selectCourseSelectionByCourseId(@Param("courseId")String courseId,
                                                           @Param("offset")Integer offset,
                                                           @Param("limit")Integer limit);
    Integer insertNewCourseSelection(CourseSelection courseSelection);
}
