package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.Page;
import top.oahnus.dto.StudentDto;
import top.oahnus.entity.Student;
import top.oahnus.exception.*;
import top.oahnus.mapper.StudentMapper;
import top.oahnus.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by oahnus on 2017/3/28
 * 22:17.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Page<List<Student>> selectStudentByProfession(String profession, Integer page, Integer limit) {
        if (StringUtil.isEmpty(profession) || page == null || limit == null) {
            throw new BadRequestParamException("请求参数错误");
        }
        List<Student> studentList = studentMapper.selectStudentByProfession(profession, (page-1)*limit, limit);
        // TODO 强行创建匿名 Map
        Integer totalRecord = studentMapper.selectRecordCount(new HashMap<String,String>(){{put("profession", profession);}});
        return new Page<>(studentList, totalRecord, page, limit);
    }

    @Override
    public Page<List<Student>> selectStudentByDepart(String depart, Integer page, Integer limit) {
        if (StringUtil.isEmpty(depart) || page == null || limit == null) {
            throw new BadRequestParamException("请求参数错误");
        }
        List<Student> studentList = studentMapper.selectStudentByDepart(depart, (page-1)*limit, limit);
        Integer totalRecord = studentMapper.selectRecordCount(new HashMap<String,String>(){{put("depart", depart);}});
        return new Page<>(studentList, totalRecord, page, limit);
    }

    @Override
    public Page<List<Student>> selectAllStudent(Integer page, Integer limit) {
        if (page == null || limit == null)
            throw new BadRequestParamException("请求参数错误");
        List<Student> studentList = studentMapper.selectAllStudent((page-1)*limit, limit);
        Integer totalRecord = studentMapper.selectRecordCount(null);
        return new Page<>(studentList, totalRecord, page, limit);
    }

    @Override
    public Student selectStudentById(String studentId) {
        if (StringUtil.isEmpty(studentId)) {
            throw new BadRequestParamException("请求参数错误");
        }
        Student student = studentMapper.selectStudentById(studentId);
        if (student == null) {
            throw new NotFoundException("数据不存在");
        }
        return student;
    }

    @Override
    public List<Student> insertStudents(List<Student> students) {
        if (students == null) throw new ReadDataFailedException("读取Excel文件失败");

        List<Student> studentList = new ArrayList<>();

        Integer count = studentMapper.insertStudents(students);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据库失败");
        } else {
            students.forEach(student -> {
                studentList.add(studentMapper.selectStudentByStudentNum(student.getStudentNum()));
            });
        }
        return studentList;
    }

    @Override
    public Student insertOneStudent(StudentDto studentDto) {
        if (studentDto == null) throw new BadRequestParamException("请求参数错误");

        Student student = new Student(studentDto);
        Integer count = studentMapper.insertOneStudent(student);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据库失败");
        } else if (count == 0) {
            throw new DataExistedException("数据已存在");
        } else {
            student = studentMapper.selectStudentByStudentNum(student.getStudentNum());
            return student;
        }
    }

    @Override
    public Integer deleteStudentById(String studentId) {
        if (StringUtil.isEmpty(studentId)) throw new BadRequestParamException("学生ID为空,请求参数错误");
        Integer count = studentMapper.deleteStudentById(studentId);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("删除学生信息失败");
        } else if (count == 0) {
            throw new NotFoundException("数据为找到");
        } else {
            // todo 删除用户权限表中的数据
            return count;
        }
    }

    @Override
    public Student updateStudent(StudentDto studentDto) {
        Student student = new Student(studentDto);
        if (student.getId() == null) {
            throw new BadRequestParamException("id不能为空");
        }
        Integer count = studentMapper.updateStudent(student);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("更新学生数据失败");
        } else {
            return student;
        }
    }
}
