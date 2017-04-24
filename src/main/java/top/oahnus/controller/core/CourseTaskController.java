package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.oahnus.Constants;
import top.oahnus.enums.ServerState;
import top.oahnus.dto.CourseTaskDto;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.CourseTask;
import top.oahnus.service.CourseTaskService;
import top.oahnus.util.FileUploadDownloadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by oahnus on 2017/4/23
 * 22:39.
 */
@RestController
@CrossOrigin
@RequestMapping("/tasks")
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

    @PostMapping("/content/upload")
    public ResponseData<String> uploadCourseContentFile(MultipartHttpServletRequest mhsr) {
        String fileContentSavedPath = mhsr.getSession().getServletContext().getRealPath("/WEB-INF/upload/task/content");
        MultipartFile file = mhsr.getFile(Constants.UPLOAD_FILE_PARAM_NAME);

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

        try {
            return FileUploadDownloadUtil.saveUploadFile(fileContentSavedPath, file, extension);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseData<>(ServerState.FILE_UPLOAD_ERROR, e.getMessage());
        }
    }

    @GetMapping("/content/download/{filename:.+}")
    public void downloadTaskContentFile(@PathVariable("filename")String filename,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException {
        String fileAdditionSavedPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/task/content");
        FileUploadDownloadUtil.downloadFile(response, filename, fileAdditionSavedPath);
    }
}
