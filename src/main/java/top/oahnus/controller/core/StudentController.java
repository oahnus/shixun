package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.annotations.NeedAdmin;
import top.oahnus.common.dto.ResultData;
import top.oahnus.common.interfaces.HttpMixin;
import top.oahnus.common.payload.pageForm.StudentPageForm;
import top.oahnus.enums.ServerState;
import top.oahnus.domain.Student;
import top.oahnus.repository.StudentRepo;
import top.oahnus.service.StudentService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 21:00
 */
@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController implements HttpMixin {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/page")
    public ResultData page(@RequestBody StudentPageForm form) {
        Page<Student> page = studentRepo.findByForm(form);
        return new ResultData().data("page", page);
    }

    @NeedAdmin
    @PostMapping
    public ResultData save(@RequestBody @Validated Student student) {
        studentService.save(student);
        return new ResultData();
    }

    @PutMapping
    public ResultData update(@RequestBody @Validated Student student) {
        studentService.update(student);
        return new ResultData();
    }

    @NeedAdmin
    @DeleteMapping("/{id}")
    public ResultData deleteStudent(@PathVariable("id") Long studentId) {
        studentService.delete(studentId);
        return new ResultData();
    }
}
