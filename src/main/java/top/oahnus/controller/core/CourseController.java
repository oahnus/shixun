package top.oahnus.controller.core;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.oahnus.Constants;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.CourseDto;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.Course;
import top.oahnus.exception.FileUplaodException;
import top.oahnus.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping
    public ResponseData<Page> getAllCourse(@RequestParam("page")Integer page,
                                           @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectAllCourse(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/profession")
    public ResponseData<Page> getCourseByProfession(@RequestParam("profession") String profession,
                                                    @RequestParam("page")Integer page,
                                                    @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectCourseByProfessionsLikeProfession(profession, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/teacher")
    public ResponseData<Page> getCourseByTeacherId(@RequestParam("teacherId")String teacherId,
                                                    @RequestParam("page")Integer page,
                                                    @RequestParam("limit")Integer limit) {
        Page<List<Course>> p = new Page<>();
        p = courseService.selectCourseByTeacherId(teacherId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/company")
    public ResponseData<Page> getCourseByCompanyId(@RequestParam("companyId")String companyId,
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
            throw new FileUplaodException("上传文件格式错误");
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
        Path path = Paths.get(fileAdditionSavedPath + File.separator + filename);
        File targetFile = path.toFile();

        if (!targetFile.exists()) {
            response.setContentType("application/json;charset=utf-8");
            try (OutputStream out = response.getOutputStream()) {
                String json = "{\"status\":\"30000\",\"msg\":\"文件未找到\"}";
                out.write(json.getBytes());
                out.flush();
            }
        } else {
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));
            byte[] bytes = Files.readAllBytes(path);
            try (OutputStream out = response.getOutputStream()) {
                out.write(bytes);
                out.flush();
            }
        }
    }
}
