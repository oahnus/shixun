package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.entity.Teacher;
import top.oahnus.enums.ServerState;
import top.oahnus.payload.ResponseData;
import top.oahnus.payload.TeacherPayload;
import top.oahnus.service.TeacherService;

/**
 * Created by oahnus on 2017/5/27
 * 17:24.
 */
@RestController
@CrossOrigin
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseData<Page> getTeacherByPage(@RequestParam("page")Integer page,
                                               @RequestParam("limit")Integer limit) {
        Page<Teacher> teachers = teacherService.getTeacherByPage(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
    }

    @GetMapping("/workerId")
    public ResponseData<Page> getTeacherByWorkerIdLike(@RequestParam("workerId")String workerId,
                                                       @RequestParam("page")Integer page,
                                                       @RequestParam("limit")Integer limit) {
        Page<Teacher> teachers = teacherService.getTeacherByWorkerIdLike(workerId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
    }

    @GetMapping("/depart")
    public ResponseData<Page> getTeacherByDepart(@RequestParam("departId")Long depart,
                                                 @RequestParam("page")Integer page,
                                                 @RequestParam("limit")Integer limit) {
        Page<Teacher> teachers = teacherService.getTeacherByDepart(depart, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
    }

    @GetMapping("/profession")
    public ResponseData<Page> getTeacherByProfession(@RequestParam("professionId")Long professionId,
                                                 @RequestParam("page")Integer page,
                                                 @RequestParam("limit")Integer limit) {
        Page<Teacher> teachers = teacherService.getTeacherByProfession(professionId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
    }

    @GetMapping("/name")
    public ResponseData<Page> getTeacherByNameLike(@RequestParam("name")String name,
                                                   @RequestParam("page")Integer page,
                                                   @RequestParam("limit")Integer limit) {
        Page<Teacher> teachers = teacherService.getTeacherByNameLike(name, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, teachers, "success");
    }

    @PostMapping
    public ResponseData<Teacher> insertTeacher(
            @Validated @RequestBody TeacherPayload payload,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Teacher teacher = teacherService.insertTeacher(payload);
        return new ResponseData<>(ServerState.SUCCESS, teacher, "success");
    }

    @PutMapping
    public ResponseData<Teacher> updateTeacher(
            @Validated @RequestBody TeacherPayload payload,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Teacher teacher = teacherService.updateTeacher(payload);
        return new ResponseData<>(ServerState.SUCCESS, teacher, "success");
    }

    @DeleteMapping("/{teacherId}")
    public ResponseData<String> deleteTeacherById(@PathVariable("teacherId")Long teacherId) {
        teacherService.deleteTeacherById(teacherId);
        return new ResponseData<>(ServerState.SUCCESS, "删除成功", "success");
    }
}
