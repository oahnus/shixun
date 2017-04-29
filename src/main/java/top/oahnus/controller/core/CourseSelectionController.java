package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.enums.CourseState;
import top.oahnus.enums.ServerState;
import top.oahnus.dto.CourseSelectionDto;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.CourseSelection;
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

    @GetMapping
    public ResponseData<Page> getCourseSelectionByCourseId(@RequestParam(value = "courseId", required = false)String courseId,
                                                           @RequestParam(value = "studentId", required = false)String studentId,
                                                           @RequestParam(value = "courseState", required = false)CourseState courseState,
                                                           @RequestParam("page")Integer page,
                                                           @RequestParam("limit")Integer limit) {
        if (courseId != null)
        {
            Page<List<CourseSelection>> p = courseSelectionService.selectCourseSelectionByCourseId(courseId, courseState, page, limit);
            return new ResponseData<>(ServerState.SUCCESS, p, "success");
        }
        if (studentId != null) {
            Page<List<CourseSelection>> p = courseSelectionService.selectCourseSelectionByStudentId(studentId, courseState, page, limit);
            return new ResponseData<>(ServerState.SUCCESS, p, "success");
        }
        return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, null, "error");
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
