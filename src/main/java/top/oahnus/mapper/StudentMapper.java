package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Student;

import java.util.List;

/**
 * Created by oahnus on 2017/3/28
 * 22:08.
 */
@Mapper
@Repository
public interface StudentMapper extends BaseMapper{
    Student selectStudentByStudentNum(@Param("studentNum")String studentNum);
    List<Student> selectStudentByProfession(@Param("profession")String profession, @Param("offset")Integer offset, @Param("limit")Integer limit);
    List<Student> selectStudentByDepart(@Param("depart")String depart, @Param("offset")Integer offset, @Param("limit")Integer limit);
    List<Student> selectAllStudent(@Param("offset")Integer offset, @Param("limit")Integer limit);
    Student selectStudentById(@Param("studentId")String studentId);
    List<Student> selectStudentByCourseId(@Param("courseId") String courseId, @Param("offset")Integer offset,@Param("limit") Integer limit);

    Integer insertStudents(List<Student> studentList);
    Integer insertOneStudent(Student student);

    Integer deleteStudentById(@Param("id")String studentId);
    Integer updateStudent(Student student);
}
