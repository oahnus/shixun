package top.oahnus.controller.administrator;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.oahnus.common.dto.ResultData;
import top.oahnus.enums.RoleEnum;
import top.oahnus.service.ExcelImportService;

/**
 * Created by oahnus on 2017/2/26.
 * ${PACKAGE_NAME}
 */
@RequestMapping("/admin")
@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    private ExcelImportService excelImportService;

    @PostMapping("/import/data")
    public ResultData importExcel(@RequestBody MultipartFile multipartFile,
                                  @RequestParam("role") RoleEnum role) {
        excelImportService.importData(multipartFile, role);
        return new ResultData();
    }
}
