package top.oahnus.controller.core;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.oahnus.Constants;
import top.oahnus.enums.ServerState;
import top.oahnus.dto.CourseDto;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.Course;
import top.oahnus.service.CourseService;
import top.oahnus.util.FileUploadDownloadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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

    @GetMapping("/{courseId}")
    public ResponseData<Course> selectCourseById(@PathVariable("courseId")String courseId) {
        Course course = courseService.selectCourseByCourseId(courseId);
        return new ResponseData<>(ServerState.SUCCESS, course, "success");
    }

    /**
     * 4.22 添加根据CourseState查询选课信息
     */
    @GetMapping
    public ResponseData<Page> getAllCourse(@RequestParam(value = "state",required = false)String state,
                                           @RequestParam("page")Integer page,
                                           @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectAllCourse(state, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/profession")
    public ResponseData<Page> getCourseByProfession(@RequestParam(value = "state",required = false)String state,
                                                    @RequestParam("profession") String profession,
                                                    @RequestParam("page")Integer page,
                                                    @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectCourseByProfessionsLikeProfession(state, profession, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/teacher")
    public ResponseData<Page> getCourseByTeacherId(@RequestParam(value = "state",required = false)String state,
                                                   @RequestParam("teacherId")String teacherId,
                                                    @RequestParam("page")Integer page,
                                                    @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectCourseByTeacherId(state, teacherId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/company")
    public ResponseData<Page> getCourseByCompanyId(@RequestParam(value = "state",required = false)String state,
                                                   @RequestParam("companyId")String companyId,
                                                   @RequestParam("page")Integer page,
                                                   @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectCourseByCompanyId(state, companyId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @PostMapping
    public ResponseData<Course> insertNewCourse(@Validated @RequestBody CourseDto courseDto,
                                                BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Course course = courseService.insertNewCourse(courseDto);
        return new ResponseData<>(ServerState.SUCCESS, course, "success");
    }

    @PutMapping
    public ResponseData<Course> updateCourse(@Validated @RequestBody CourseDto courseDto) {
        Course course = courseService.updateCourse(courseDto);
        return new ResponseData<>(ServerState.SUCCESS, course, "success");
    }

    @PutMapping("/state")
    public ResponseData<Integer> changeCourseState(@RequestParam("profession")String profession,
                                                   @RequestParam("state")String state) {
        Integer count = courseService.changeCourseState(profession, state);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }

    @DeleteMapping("/{courseId}")
    public ResponseData deleteById(@PathVariable("courseId")String courseId) {
        courseService.deleteCourseById(courseId);
        return new ResponseData(ServerState.SUCCESS, "success");
    }

    /**
     * 课程附件上传
     * @param mhsr multipart/form-data
     * @return 文件路径
     * @throws IOException
     */
    @PostMapping("/addition/upload")
    public ResponseData<String> uploadCourseAddition(MultipartHttpServletRequest mhsr) throws IOException {
        String fileAdditionSavedPath = mhsr.getSession().getServletContext().getRealPath("/WEB-INF/upload/course/addition/");
        MultipartFile file = mhsr.getFile(Constants.UPLOAD_FILE_PARAM_NAME);

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

        if (Stream.of(Constants.ALLOWED_UPLOAD_FILE_EXTENSIONS)
                .anyMatch(ext -> ext.equals(extension))) {
            String uuid = UUID.randomUUID().toString();
            File additionFile = new File(fileAdditionSavedPath, uuid + extension);
            FileUtils.copyInputStreamToFile(file.getInputStream(), additionFile);
            return new ResponseData<>(ServerState.SUCCESS, uuid + extension, "success");
        } else {
            return new ResponseData<>(ServerState.FILE_UPLOAD_ERROR, "上传文件格式错误");
        }
    }

    /**
     * 下载课程附件
     * @param request request
     * @param response response
     * @param filename filename
     * @throws IOException Exception
     */
    @GetMapping("/addition/download/{filename:.+}")
    public void downloadCourseAddition(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @PathVariable String filename) throws IOException {
        String fileAdditionSavedPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/course/addition");
        FileUploadDownloadUtil.downloadFile(response, filename, fileAdditionSavedPath);
    }
}
