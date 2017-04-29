package top.oahnus.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import top.oahnus.Constants;
import top.oahnus.enums.ServerState;
import top.oahnus.dto.ResponseData;
import top.oahnus.exception.FileUplaodException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by oahnus on 2017/4/24
 * 12:13.
 */
public class FileUploadDownloadUtil {
    public static boolean removeRubbishUplaodFile(String uploadFileSavedPath) throws IOException {
        Stream<Path> fileStream = Files.walk(Paths.get(uploadFileSavedPath));
        fileStream.filter(path -> !path.getFileName().startsWith("FILE")).forEach(path -> path.toFile().delete());
        return true;
    }

    public static String markUploadFile(String fileSavedPath, String filename) {
        File waitRemarkedFile = Paths.get(fileSavedPath + File.separator + filename).toFile();
        if (waitRemarkedFile.exists()) {
            String newFileName = fileSavedPath + File.separator + "FILE" + filename;
            waitRemarkedFile.renameTo(new File(newFileName));
            return newFileName;
        } else {
            return null;
        }
    }

    public static ResponseData<String> saveUploadFile(String fileSavedPath, MultipartFile file, String extension) throws IOException {
        if (Stream.of(Constants.ALLOWED_UPLOAD_FILE_EXTENSIONS)
                .anyMatch(ext -> ext.equals(extension))) {
            String uuid = UUID.randomUUID().toString();
            File additionFile = new File(fileSavedPath, uuid + extension);
            FileUtils.copyInputStreamToFile(file.getInputStream(), additionFile);
            return new ResponseData<>(ServerState.SUCCESS, uuid + extension, "success");
        } else {
            throw new FileUplaodException("上传文件格式错误");
        }
    }

    public static void downloadFile(HttpServletResponse response, String filename, String fileSavedPath) throws IOException {
        Path path = Paths.get(fileSavedPath + File.separator + filename);
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
