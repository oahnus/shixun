package top.oahnus.controller.administrator;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import top.oahnus.Constants;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.Company;
import top.oahnus.entity.Student;
import top.oahnus.entity.Teacher;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.DataFormatException;
import top.oahnus.service.CompanyService;
import top.oahnus.util.ExcelReaderUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * ${PACKAGE_NAME}
 */
@RequestMapping("/admin")
@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/students")
    public ResponseData<List<Company>> insertCompanyByAdminUploadExcel(MultipartHttpServletRequest mhsr) throws IOException {
        String tempPath = mhsr.getSession().getServletContext().getRealPath("/WEB-INF/temp/");
        MultipartFile file = mhsr.getFile(Constants.UPLOAD_FILE_PARAM_NAME);
        File excel = new File(tempPath, file.getOriginalFilename());

        FileUtils.copyInputStreamToFile(file.getInputStream(), excel);

        List<Company> companies = ExcelReaderUtil.readExcelFile(excel, AuthType.COMPANY);

        if(companies == null) throw new DataFormatException("无法从文件中获取公司信息");

        List<Company> companyList = companyService.insertCompanies(companies);

        return new ResponseData<>(ServerState.SUCCESS, companyList, "");
    }
}
