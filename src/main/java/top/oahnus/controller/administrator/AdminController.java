package top.oahnus.controller.administrator;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import top.oahnus.Constants;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.CompanyDto;
import top.oahnus.dto.ResponseData;
import top.oahnus.dto.StudentDto;
import top.oahnus.entity.Company;
import top.oahnus.entity.Student;
import top.oahnus.entity.Teacher;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.DataFormatException;
import top.oahnus.exception.ReadDataFailedException;
import top.oahnus.service.CompanyService;
import top.oahnus.service.StudentService;
import top.oahnus.service.TeacherService;
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

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    /**
     * 从Excel批量插入公司信息
     */
    @PostMapping("/companies")
    public ResponseData<List<Company>> insertCompanyByAdminUploadExcel(MultipartHttpServletRequest mhsr) throws IOException {
        String tempPath = mhsr.getSession().getServletContext().getRealPath("/WEB-INF/temp/");
        MultipartFile file = mhsr.getFile(Constants.UPLOAD_FILE_PARAM_NAME);
        File excel = new File(tempPath, file.getOriginalFilename());

        FileUtils.copyInputStreamToFile(file.getInputStream(), excel);

        List<Company> companies = ExcelReaderUtil.readExcelFile(excel, AuthType.COMPANY);

        if(companies == null) throw new ReadDataFailedException("无法从文件中获取公司信息");

        List<Company> companyList = companyService.insertCompanies(companies);

        return new ResponseData<>(ServerState.SUCCESS, companyList, "");
    }

    /**
     * 从Excel批量插入教师信息
     */
    @PostMapping("/teachers")
    public ResponseData<List<Teacher>> insertTeacherByAdminUploadExcel(MultipartHttpServletRequest mhsr) throws IOException{
        String tempPath = mhsr.getSession().getServletContext().getRealPath("/WEB-INF/temp/");
        MultipartFile file = mhsr.getFile(Constants.UPLOAD_FILE_PARAM_NAME);
        File excel = new File(tempPath, file.getOriginalFilename());

        FileUtils.copyInputStreamToFile(file.getInputStream(), excel);

        List<Teacher> teachers = ExcelReaderUtil.readExcelFile(excel, AuthType.TEACHER);

        if (teachers == null) throw new ReadDataFailedException("无法从文件中获取教师信息");

        List<Teacher> teacherList = teacherService.insertTeachers(teachers);

        return new ResponseData<>(ServerState.SUCCESS, teacherList, "");
    }

    /**
     * 从Excel批量插入学生信息
     */
    @PostMapping("/students")
    public ResponseData<List<Student>> insertStudentByAdminUploadExcel(MultipartHttpServletRequest mhsr) throws IOException {
        String tempPath = mhsr.getSession().getServletContext().getRealPath("/WEB-INF/temp/");
        MultipartFile file = mhsr.getFile(Constants.UPLOAD_FILE_PARAM_NAME);
        File excel = new File(tempPath, file.getOriginalFilename());

        FileUtils.copyInputStreamToFile(file.getInputStream(), excel);

        List<Student> students = ExcelReaderUtil.readExcelFile(excel, AuthType.STUDENT);

        if (students == null) throw new ReadDataFailedException("无法从文件中获取学生信息");

        List<Student> studentList = studentService.insertStudents(students);

        return new ResponseData<>(ServerState.SUCCESS, studentList, "");
    }

}
