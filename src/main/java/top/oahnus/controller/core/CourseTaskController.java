package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.CourseTaskDto;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.CourseTask;
import top.oahnus.service.CourseTaskService;

import java.util.List;

/**
 * Created by oahnus on 2017/4/23
 * 22:39.
 */
@RestController
@CrossOrigin
@RequestMapping("/task")
public class CourseTaskController {
    @Autowired
    private CourseTaskService courseTaskService;

    @GetMapping
    public ResponseData<List<CourseTask>> selectCourseTaskByCourseId(@RequestParam("courseId")String courseId) {
        List<CourseTask> courseTasks = courseTaskService.selectCourseTaskByCourseId(courseId);
        return new ResponseData<>(ServerState.SUCCESS, courseTasks, "success");
    }

    @PostMapping
    public ResponseData<Integer> insertCourseTask(@Validated @RequestBody CourseTaskDto courseTaskDto) {
        Integer count = courseTaskService.insertNewCourseTask(courseTaskDto);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }

    @PutMapping
    public ResponseData<Integer> updateCourseTask(@Validated @RequestBody CourseTaskDto courseTaskDto) {
        Integer count = courseTaskService.updateCourseTask(courseTaskDto);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }

    @DeleteMapping("/{taskId}")
    public ResponseData<Integer> deleteCourseTask(@PathVariable("taskId")String taskId) {
        Integer count = courseTaskService.deleteCourseTaskById(taskId);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }
}
