package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.dto.ResultData;
import top.oahnus.common.interfaces.HttpMixin;
import top.oahnus.domain.Task;
import top.oahnus.service.TaskService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by oahnus on 2018/5/14
 * 10:33.
 */
@RestController
@RequestMapping("/task")
public class TaskController implements HttpMixin{
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResultData list(@RequestParam("courseId")Long courseId) {
        List<Task> taskList = taskService.list(courseId);
        return new ResultData().data("list", taskList);
    }

    @GetMapping("/auth")
    public ResultData listByAuth(@RequestParam("courseId")Long courseId) {
        List<Task> taskList = taskService.listByAuth(courseId, curAuthId());
        return new ResultData().data("list", taskList);
    }

    @PostMapping
    public ResultData create(@RequestBody @Valid Task task) {
        Long authId = curAuthId();
        taskService.save(task, authId);
        return new ResultData();
    }

    @PutMapping
    public ResultData update(@RequestBody @Valid Task task) {
        Long authId = curAuthId();
        taskService.update(task, authId);
        return new ResultData();
    }

    @DeleteMapping("{id}")
    public ResultData deleteById(@PathVariable("id") Long taskId) {
        Long authId = curAuthId();
        taskService.delete(taskId, authId);
        return new ResultData();
    }
}
