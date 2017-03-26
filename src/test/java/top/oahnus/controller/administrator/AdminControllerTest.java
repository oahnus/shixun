package top.oahnus.controller.administrator;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by oahnus on 2017/2/27.
 */
@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test(){

    }

    @Test
    public void insertCompanyByAdminUploadExcel() throws Exception {
        File excel = new File(System.getProperty("user.dir")+"/src/test/excel", "company.xlsx");

        mockMvc.perform(
                fileUpload("/admin/companies")
                        .file(new MockMultipartFile(
                                "upload_file", excel.getName(), "xlsx", new FileInputStream(excel)))
        ).andDo(print());
    }
//
//    @Test
//    public void insertTeacherByAdminUploadExcel() throws Exception {
//        File excel = new File(System.getProperty("user.dir")+"/src/test/resources", "teacher.xlsx");
//
//        mockMvc.perform(
//                fileUpload("/admin/teachers")
//                        .file(new MockMultipartFile(
//                                "upload_file", excel.getName(), "xlsx", new FileInputStream(excel)))
//        ).andDo(print());
//    }
//
//    @Test
//    public void insertStudentByAdminUploadExcel() throws Exception {
//        File excel = new File(System.getProperty("user.dir")+"/src/test/resources", "student.xlsx");
//
//        mockMvc.perform(
//                fileUpload("/admin/students")
//                        .file(new MockMultipartFile(
//                                "upload_file", excel.getName(), "xlsx", new FileInputStream(excel)))
//        ).andDo(print());
//    }
}