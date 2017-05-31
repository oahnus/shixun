package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.entity.Student;
import top.oahnus.enums.ServerState;
import top.oahnus.payload.ResponseData;
import top.oahnus.payload.StudentPayload;
import top.oahnus.service.StudentService;

/**
 * Created by oahnus on 2017/5/27
 * 18:49.
 */
@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseData<Page> findStudentByPage(@RequestParam("page")Integer page,
                                                @RequestParam("limit")Integer limit) {
        Page p = studentService.getStudentByPage(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "ok");
    }

    @GetMapping("/depart")
    public ResponseData<Page> findStudentByDepart(@RequestParam("departId")Long depart,
                                                  @RequestParam("page")Integer page,
                                                  @RequestParam("limit")Integer limit) {
        Page p = studentService.findByDepart(depart, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "ok");
    }

    @GetMapping("/profession")
    public ResponseData<Page> findStudentByProfession(@RequestParam("professionId")Long profession,
                                                      @RequestParam("page")Integer page,
                                                      @RequestParam("limit")Integer limit) {
        Page p = studentService.findByProfession(profession, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "ok");
    }

    @GetMapping("/name")
    public ResponseData<Page> findStudentByName(@RequestParam("name")String name,
                                                @RequestParam("page")Integer page,
                                                @RequestParam("limit")Integer limit) {
        Page p = studentService.findByNameLike(name, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "ok");
    }

    @GetMapping("/{studentId}")
    public ResponseData<Student> getById(@PathVariable("studentId")Long studentId) {
        Student student = studentService.getById(studentId);
        return new ResponseData<>(ServerState.SUCCESS, student,"ok");
    }

    @PostMapping
    public ResponseData<Student> insertStudent(@Validated @RequestBody StudentPayload payload,
                                               BindingResult result) {
        if(result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Student student = studentService.insertStudent(payload);
        return new ResponseData<>(ServerState.SUCCESS, student, "ok");
    }

    @PutMapping
    public ResponseData<Student> updateStudent(@Validated @RequestBody StudentPayload payload,
                                               BindingResult result) {
        if(result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Student student = studentService.updateStudent(payload);
        return new ResponseData<>(ServerState.SUCCESS, student, "ok");
    }

    @DeleteMapping("/{studentId}")
    public ResponseData<String> deleteById(@PathVariable("studentId")Long studentId) {
        studentService.deleteStudentById(studentId);
        return new ResponseData<>(ServerState.SUCCESS, "删除成功","ok");
    }
}
