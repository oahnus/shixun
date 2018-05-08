package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.CourseSelection;

import java.util.Date;
import java.util.List;

/**
 * Created by oahnus on 2017/4/8
 * 13:13.
 */
@Mapper
@Repository
public interface CourseSelectionMapper extends BaseMapper {
    List<CourseSelection> selectCourseSelectionByStudentId(@Param("studentId")String studentId,
                                                           @Param("courseState")Integer state,
                                                           @Param("offset")Integer offset,
                                                           @Param("limit")Integer limit);
    List<CourseSelection> selectCourseSelectionByCourseId(@Param("courseId")String courseId,
                                                          @Param("courseState")Integer state,
                                                          @Param("offset")Integer offset,
                                                          @Param("limit")Integer limit);

    CourseSelection selectCourseSelectionByStudentIdAndCourseId(@Param("studentId")String studentId,
                                                                                   @Param("courseId")String courseId);

    Integer insertNewCourseSelection(CourseSelection courseSelection);

    Integer deleteCourseSelectionById(@Param("courseSelectionId")String courseSelectionId);
}
