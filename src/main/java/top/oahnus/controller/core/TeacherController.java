package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/professions")
    public ResponseData<Page> getTeacherByProfession(
            @RequestParam("profession")String profession,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit) {
        Page<List<Teacher>> p = new Page<>();
        p = teacherService.selectTeacherByProfession(profession, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/departs")
    public ResponseData<Page> getTeacherByDepart(
            @RequestParam("depart")String depart,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit) {
        Page<List<Teacher>> p = new Page<>();
        p = teacherService.selectTeacherByDepart(depart, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @PostMapping
    public ResponseData<Teacher> insertOneTeacher(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherService.insertOneTeacher(teacherDto);
        return new ResponseData<>(ServerState.SUCCESS, teacher, "success");
    }

    @PutMapping
    public ResponseData<Teacher> updateTeacher(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherService.updateTeacher(teacherDto);
        return new ResponseData<>(ServerState.SUCCESS, teacher, "success");
    }

    @DeleteMapping("/{id}")
    public ResponseData<String> deleteTeacherById(@PathVariable("id")String teacherId) {
        teacherService.deleteTeacherById(teacherId);
        return new ResponseData<>(ServerState.SUCCESS, "success");
    }
}
