package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.enums.ServerState;
import top.oahnus.dto.Page;
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

    @GetMapping("/{studentId}")
    public ResponseData<Student> selectStudentById(@PathVariable("studentId")String studentId) {
        Student student = studentService.selectStudentById(studentId);
        return new ResponseData<>(ServerState.SUCCESS, student, "success");
    }

    /**
     * 单条插入学生信息
     */
    @PostMapping
    public ResponseData<Student> insertNewStudent(@Validated @RequestBody StudentDto studentDto,
                                                  BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Student student = studentService.insertOneStudent(studentDto);
        return new ResponseData<>(ServerState.SUCCESS, student, "success");
    }

    @GetMapping
    public ResponseData<Page> selectAllStudent(@RequestParam("page")Integer page,
                                               @RequestParam("limit")Integer limit) {
        Page<List<Student>> p = studentService.selectAllStudent(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    /**
     * 分页获取学院学生
     */
    @GetMapping("/depart")
    public ResponseData<Page> selectStudentsByDepart(@RequestParam("depart")String depart,
                                                     @RequestParam("page")Integer page,
                                                     @RequestParam("limit") Integer limit){
        Page<List<Student>> p = studentService.selectStudentByDepart(depart, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    /**
     * 分页获取专业学生
     */
    @GetMapping("/profession")
    public ResponseData<Page> selectStudentsByProfession(@RequestParam("profession")String profession,
                                                         @RequestParam("page")Integer page,
                                                         @RequestParam("limit")Integer limit){
        Page<List<Student>> p = studentService.selectStudentByProfession(profession, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
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
