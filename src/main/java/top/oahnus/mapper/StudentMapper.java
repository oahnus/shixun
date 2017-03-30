package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.Student;

import java.util.List;

/**
 * Created by oahnus on 2017/3/28
 * 22:08.
 */
@Mapper
public interface StudentMapper {
    Student selectStudentByStudentNum(@Param("studentNum")String studentNum);
    List<Student> selectStudentByProfession(@Param("profession")String profession, @Param("offset")Integer offset, @Param("limit")Integer limit);
    List<Student> selectStudentByDepart(@Param("depart")String depart, @Param("offset")Integer offset, @Param("limit")Integer limit);

    Integer insertIntoStudent(List<Student> studentList);
    Integer deleteStudentById(@Param("id")String studentId);
    Integer updateStudent(Student student);
}
