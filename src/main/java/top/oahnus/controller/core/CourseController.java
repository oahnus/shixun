package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.annotations.NeedAdmin;
import top.oahnus.common.dto.ResultData;
import top.oahnus.common.interfaces.HttpMixin;
import top.oahnus.common.payload.CourseForm;
import top.oahnus.common.payload.pageForm.CoursePageForm;
import top.oahnus.domain.Course;
import top.oahnus.repository.CourseRepo;
import top.oahnus.service.CourseService;

import javax.validation.Valid;

/**
 * Created by oahnus on 2017/3/11.
 * 20:20
 */
@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController implements HttpMixin {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepo courseRepo;

    @PutMapping("/switch")
    @NeedAdmin
    public ResultData changeState(@RequestBody @Valid CourseForm form) {
        courseService.switchState(form);
        return new ResultData();
    }

    @PostMapping("/page")
    public ResultData listByPage(@RequestBody CoursePageForm form) {
        Page<Course> page = courseRepo.findByForm(form);
        return new ResultData().data("page", page);
    }

    @NeedAdmin
    @PostMapping
    public ResultData save(@RequestBody @Valid Course course) {
        courseService.save(course);
        return new ResultData();
    }

    @PutMapping
    public ResultData update(@RequestBody @Valid Course course) {
        courseService.update(course);
        return new ResultData();
    }

    @NeedAdmin
    @DeleteMapping("/{id}")
    public ResultData deleteById(@PathVariable("id")Long courseId) {
        courseService.deleteById(courseId);
        return new ResultData();
    }
}