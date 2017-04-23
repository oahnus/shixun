package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.dto.TeacherDto;
import top.oahnus.entity.Teacher;
import top.oahnus.service.TeacherService;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 21:29
 */
@CrossOrigin
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/{teacherId}")
    public ResponseData<Teacher> selectTeacherById(@PathVariable("teacherId")String teacherId) {
        Teacher teacher = teacherService.selectTeacherById(teacherId);
        return new ResponseData<>(ServerState.SUCCESS, teacher, "success");
    }

    @GetMapping
    public ResponseData<Page> getAllTeacher(@RequestParam("page")Integer page,
                                            @RequestParam("limit")Integer limit) {
        Page<List<Teacher>> p = new Page<>();
        p = teacherService.selectAllTeacher(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/profession")
    public ResponseData<Page> getTeacherByProfession(
            @RequestParam("profession")String profession,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit) {
        Page<List<Teacher>> p = new Page<>();
        p = teacherService.selectTeacherByProfession(profession, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/depart")
    public ResponseData<Page> getTeacherByDepart(
            @RequestParam("depart")String depart,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit) {
        Page<List<Teacher>> p = new Page<>();
        p = teacherService.selectTeacherByDepart(depart, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @PostMapping
    public ResponseData<Teacher> insertOneTeacher(@Validated @RequestBody TeacherDto teacherDto,
                                                  BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Teacher teacher = teacherService.insertOneTeacher(teacherDto);
        return new ResponseData<>(ServerState.SUCCESS, teacher, "success");
    }

    @PutMapping
    public ResponseData<Teacher> updateTeacher(@Validated @RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherService.updateTeacher(teacherDto);
        return new ResponseData<>(ServerState.SUCCESS, teacher, "success");
    }

    @DeleteMapping("/{id}")
    public ResponseData<String> deleteTeacherById(@PathVariable("id")String teacherId) {
        teacherService.deleteTeacherById(teacherId);
        return new ResponseData<>(ServerState.SUCCESS, "success");
    }
}
