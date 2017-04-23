package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.Course;
import top.oahnus.enums.CourseState;

import java.util.List;

/**
 * Created by oahnus on 2017/3/31
 * 22:53.
 */
@Mapper
public interface CourseMapper extends BaseMapper {
    List<Course> selectAllCourse(@Param("state")Integer state, @Param("offset") Integer offset, @Param("limit")Integer limit);
    List<Course> selectCourseByProfessionsLikeProfession(@Param("state")Integer state, @Param("profession")String profession, @Param("offset")Integer offset, @Param("limit")Integer limit);
    List<Course> selectCourseByNameLikeCourseName(@Param("state")Integer state, @Param("courseName")String courseName, @Param("offset")Integer offset, @Param("limit")Integer limit);
    List<Course> selectCourseByTeacherId(@Param("state")Integer state, @Param("teacherId")String teacherId, @Param("offset") Integer offset, @Param("limit")Integer limit);
    List<Course> selectCourseByCompanyId(@Param("state")Integer state, @Param("companyId")String companyId, @Param("offset") Integer offset, @Param("limit")Integer limit);
    Course selectCourseByNameAndTeacherIdAndCompanyId(@Param("name")String name, @Param("teacherId")String teacherId, @Param("companyId")String companyId);
    Course selectCourseById(@Param("courseId")String courseId);

    Integer insertNewCourse(Course course);

    Integer updateCourse(Course course);
    Integer changeCourseState(@Param("profession")String profession, @Param("state")Integer state);

    Integer deleteCourseById(@Param("id")String courseId);
}
