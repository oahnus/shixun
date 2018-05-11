package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.dto.ResultData;
import top.oahnus.service.TeacherService;

/**
 * Created by oahnus on 2017/2/26.
 * 21:29
 */
@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/test")
    public ResultData test() {
        return new ResultData().data("name", "tom");
    }
}
