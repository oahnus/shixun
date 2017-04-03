package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.Course;

import java.util.List;

/**
 * Created by oahnus on 2017/3/31
 * 22:53.
 */
@Mapper
public interface CourseMapper {
    List<Course> selectAllCourse(@Param("offset") Integer offset, @Param("limit")Integer limit);
    List<Course> selectCourseByProfessionsLikeProfession(@Param("profession")String profession, @Param("offset")Integer offset, @Param("limit")Integer limit);
    List<Course> selectCourseByTeacherId(@Param("teacherId")String teacherId, @Param("offset") Integer offset, @Param("limit")Integer limit);
    List<Course> selectCourseByCompanyId(@Param("companyId")String companyId, @Param("offset") Integer offset, @Param("limit")Integer limit);
    Integer selectCountCourseByCondition(@Param("teacherId")String teacherId, @Param("companyId")String companyId, @Param("profession")String profession);

    Course selectCourseByNameAndTeacherIdAndCompanyId(@Param("name")String name, @Param("teacherId")String teacherId, @Param("companyId")String companyId);

    Integer insertNewCourse(Course course);

//    Integer updateCourse(Course course);

    Integer deleteCourseById(@Param("id")String courseId);
}
