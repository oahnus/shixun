package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.ResponseData;
import top.oahnus.dto.TeacherDto;
import top.oahnus.entity.Teacher;
import top.oahnus.service.TeacherService;

import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 21:29
 */
@CrossOrigin
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PutMapping("/teachers")
    public String updateTeacher(@Validated @RequestBody TeacherDto teacherDto) {
        System.out.println(teacherDto.getWorkerId());
        return "SUCCESS";
    }

    @GetMapping("/teachers/depart")
    public ResponseData<List<Teacher>> getTeacherByDepart(String depart, int page, int size) {
        List<Teacher> teachers = teacherService.getTeachersByDepart(depart, page, size);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
    }

    @GetMapping("/teachers/profession")
    public ResponseData<List<Teacher>> getTeacherByProfession(String profession, int page, int size) {
        List<Teacher> teachers = teacherService.getTeachersByDepart(profession, page, size);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
    }


}
