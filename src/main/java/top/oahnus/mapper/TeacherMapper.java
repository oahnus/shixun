package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.Teacher;

import java.util.List;

/**
 * Created by oahnus on 2017/3/28
 * 0:41.
 */
@Mapper
public interface TeacherMapper extends BaseMapper{
    Teacher selectTeacherByWorkerId(@Param("worker_id")String workerId);
    List<Teacher> selectTeacherByProfession(@Param("profession")String profession, @Param("offset")Integer offset, @Param("limit")Integer limit);
    List<Teacher> selectTeacherByDepart(@Param("depart")String depart, @Param("offset")Integer offset, @Param("limit")Integer limit);
    List<Teacher> selectAllTeacher(@Param("offset")Integer offset, @Param("limit")Integer limit);
    Teacher selectTeacherById(@Param("teacherId")String teacherId);

    Integer insertTeachers(List<Teacher> teacherList);
    Integer insertOneTeacher(Teacher teacher);
    Integer deleteTeacherById(@Param("id")String teacherId);
    Integer updateTeacher(Teacher teacher);
}
