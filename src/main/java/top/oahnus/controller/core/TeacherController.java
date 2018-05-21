package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.annotations.NeedAdmin;
import top.oahnus.common.dto.ResultData;
import top.oahnus.common.interfaces.HttpMixin;
import top.oahnus.common.payload.pageForm.TeacherPageForm;
import top.oahnus.domain.Teacher;
import top.oahnus.repository.TeacherRepo;
import top.oahnus.service.TeacherService;

import javax.validation.Valid;

/**
 * Created by oahnus on 2017/2/26.
 * 21:29
 */
@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController implements HttpMixin {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherRepo teacherRepo;

    @PostMapping("/page")
    public ResultData listByForm(@RequestBody TeacherPageForm form) {
        Page<Teacher> page = teacherRepo.findByForm(form);
        return new ResultData().data("page", page);
    }

    @NeedAdmin
    @PostMapping
    public ResultData save(@RequestBody @Validated Teacher teacher) {
        teacherService.save(teacher);
        return new ResultData();
    }

    @PutMapping
    public ResultData update(@RequestBody @Validated Teacher teacher) {
        teacherService.update(teacher);
        return new ResultData();
    }
    @NeedAdmin
    @DeleteMapping("/{id}")
    public ResultData deleteById(@PathVariable("id")Long teacherId) {
        teacherService.delete(teacherId);
        return new ResultData();
    }
}
