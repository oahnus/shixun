package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.ResponseData;
import top.oahnus.dto.StudentDto;
import top.oahnus.entity.Student;
import top.oahnus.service.StudentService;

import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 21:00
 */
@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 单条插入学生信息
     */
    @PostMapping
    public ResponseData<Student> insertNewStudent(@RequestBody StudentDto studentDto) {
        Student student = studentService.insertOneStudent(studentDto);
        return new ResponseData<>(ServerState.SUCCESS, student, "success");
    }


    /**
     * 分页获取学院学生
     */
    @GetMapping("/depart")
    public ResponseData<List<Student>> selectStudentsByDepart(String depart, int page, int size){
        List<Student> students = studentService.selectStudentByDepart(depart, page, size);
        return new ResponseData<>(ServerState.SUCCESS, students, "success");
    }

    /**
     * 分页获取专业学生
     */
    @GetMapping("/profession")
    public ResponseData<List<Student>> selectStudentsByProfession(String profession, int page, int size){
        List<Student> students = studentService.selectStudentByProfession(profession, page, size);
        return new ResponseData<>(ServerState.SUCCESS, students, "success");
    }

    /**
     * 更新单条学生记录
     */
    @PutMapping
    public ResponseData<Student> updateStudent(@Validated @RequestBody StudentDto studentDto) {
        Student stu = studentService.updateStudent(studentDto);
        return new ResponseData<>(ServerState.SUCCESS, stu, "success");
    }

    /**
     * 根据学生id删除单条学生数据
     */
    @DeleteMapping("/{studentId}")
    public ResponseData<String> deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudentById(studentId);
        return new ResponseData<>(ServerState.SUCCESS, "SUCCESS", "success");
    }
}
