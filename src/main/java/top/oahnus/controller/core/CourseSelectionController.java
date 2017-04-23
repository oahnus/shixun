package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.CourseSelectionDto;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.CourseSelection;
import top.oahnus.mapper.CourseSelectionMapper;
import top.oahnus.service.CourseSelectionService;

import java.util.List;

/**
 * Created by oahnus on 2017/3/11.
 * 20:21
 */
@CrossOrigin
@RestController
@RequestMapping("/courseSelection")
public class CourseSelectionController {
    @Autowired
    private CourseSelectionService courseSelectionService;

    @GetMapping("/course")
    public ResponseData<Page> getCourseSelectionByCourseId(@RequestParam("courseId")String courseId,
                                                           @RequestParam("page")Integer page,
                                                           @RequestParam("limit")Integer limit) {
        Page<List<CourseSelection>> p = courseSelectionService.selectCourseSelectionByCourseId(courseId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/student")
    public ResponseData<Page> getCourseSelectionByStudentId(@RequestParam("studentId")String studentId,
                                                           @RequestParam("page")Integer page,
                                                           @RequestParam("limit")Integer limit) {
        Page<List<CourseSelection>> p = courseSelectionService.selectCourseSelectionByStudentId(studentId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @PostMapping
    public ResponseData<CourseSelection> insertCourseSelection(@Validated @RequestBody CourseSelectionDto courseSelectionDto,
                                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }

        CourseSelection courseSelection = courseSelectionService.insertNewCourseSelection(courseSelectionDto);
        return new ResponseData<>(ServerState.SUCCESS, courseSelection, "success");
    }

    @DeleteMapping("/{courseSelectionId}")
    public ResponseData<Integer> deleteCourseSelection(@PathVariable("courseSelectionId")String courseSelectionId) {
        Integer count = courseSelectionService.deleteCourseSelectionById(courseSelectionId);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }
}
