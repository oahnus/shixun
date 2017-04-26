package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.oahnus.Constants;
import top.oahnus.enums.ServerState;
import top.oahnus.dto.ResponseData;
import top.oahnus.dto.TaskResultDto;
import top.oahnus.entity.TaskResult;
import top.oahnus.service.TaskResultService;
import top.oahnus.util.FileUploadDownloadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by oahnus on 2017/4/24
 * 17:23.
 */
@RestController
@CrossOrigin
@RequestMapping("/results")
public class TaskResultController {
    @Autowired
    private TaskResultService taskResultService;

    @GetMapping
    public ResponseData<List<TaskResult>> selectTaskResultByStudentAndTask(@RequestParam(value = "courseId",required = false)String courseId,
                                                                           @RequestParam(value = "studentId",required = false)String studentId,
                                                                           @RequestParam(value = "taskId", required = false)String taskId) {
        List<TaskResult> results = taskResultService.selectTaskResult(courseId, studentId, taskId);
        return new ResponseData<>(ServerState.SUCCESS, results, "success");
    }

    @GetMapping("/{id}")
    public ResponseData<TaskResult> selectTaskResultById(@PathVariable("id")String id) {
        TaskResult taskResult = taskResultService.selectTaskResultById(id);
        return new ResponseData<>(ServerState.SUCCESS, taskResult, "success");
    }

    @PostMapping
    public ResponseData<Integer> insertTaskResult(@RequestBody @Validated TaskResultDto taskResultDto) {
        Integer count = taskResultService.insertNewTaskResult(taskResultDto);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }

    @PutMapping
    public ResponseData<Integer> updateTaskResult(@RequestBody @Validated TaskResultDto taskResultDto) {
        Integer count = taskResultService.updateTaskResult(taskResultDto);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }

    @DeleteMapping("/{id}")
    public ResponseData<Integer> deleteTaskResultById(@PathVariable("id")String id) {
        Integer count = taskResultService.deleteTaskResult(id);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }

    @PostMapping("/upload")
    public ResponseData<String> uploadTaskResultFile(MultipartHttpServletRequest mhsr) {
        String fileResultSavedPath = mhsr.getSession().getServletContext().getRealPath("/WEB-INF/upload/result");
        MultipartFile file = mhsr.getFile(Constants.UPLOAD_FILE_PARAM_NAME);

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

        try {
            return FileUploadDownloadUtil.saveUploadFile(fileResultSavedPath, file, extension);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseData<>(ServerState.FILE_UPLOAD_ERROR, e.getMessage());
        }
    }

    @GetMapping("/download/{filename:.+}")
    public void downloadTaskResultFile(@PathVariable("filename")String filename,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException {
        String fileResultSavedPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/result");
        FileUploadDownloadUtil.downloadFile(response, filename, fileResultSavedPath);
    }
}
