package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.CourseDto;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.Course;
import top.oahnus.service.CourseService;

import java.util.List;

/**
 * Created by oahnus on 2017/3/11.
 * 20:20
 */
@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseData<Page> getAllCourse(@RequestParam("page")Integer page,
                                           @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectAllCourse(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/profession/{profession}")
    public ResponseData<Page> getCourseByProfession(@PathVariable("profession")String profession,
                                                    @RequestParam("page")Integer page,
                                                    @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectCourseByProfessionsLikeProfession(profession, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseData<Page> getCourseByTeacherId(@PathVariable("teacherId")String teacherId,
                                                    @RequestParam("page")Integer page,
                                                    @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectCourseByTeacherId(teacherId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/teacher/{companyId}")
    public ResponseData<Page> getCourseByCompanyId(@PathVariable("companyId")String companyId,
                                                   @RequestParam("page")Integer page,
                                                   @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectCourseByCompanyId(companyId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @PostMapping
    public ResponseData<Course> insertNewCourse(@Validated @RequestBody CourseDto courseDto) {
        Course course = courseService.insertNewCourse(courseDto);
        return new ResponseData<>(ServerState.SUCCESS, course, "success");
    }

    @PutMapping
    public ResponseData<Course> updateCourse(@Validated @RequestBody CourseDto courseDto) {
        Course course = courseService.updateCourse(courseDto);
        return new ResponseData<>(ServerState.SUCCESS, course, "success");
    }

    @DeleteMapping("/{courseId}")
    public ResponseData deleteById(@PathVariable("courseId")String courseId) {
        courseService.deleteCourseById(courseId);
        return new ResponseData(ServerState.SUCCESS, "success");
    }
}
