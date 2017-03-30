package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.controller.ServerState;
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
    public ResponseData<List<Teacher>> getTeacherByProfession(
            @RequestParam("profession")String profession,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit) {
        List<Teacher> teachers = new ArrayList<>();
        teachers = teacherService.selectTeacherByProfession(profession, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
    }

    @GetMapping("/departs")
    public ResponseData<List<Teacher>> getTeacherByDepart(
            @RequestParam("depart")String depart,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit) {
        List<Teacher> teachers = new ArrayList<>();
        teachers = teacherService.selectTeacherByDepart(depart, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
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
