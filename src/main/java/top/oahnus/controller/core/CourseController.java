package top.oahnus.controller.core;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.oahnus.Constants;
import top.oahnus.enums.ServerState;
import top.oahnus.payload.ResponseData;
import top.oahnus.util.FileUploadDownloadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
