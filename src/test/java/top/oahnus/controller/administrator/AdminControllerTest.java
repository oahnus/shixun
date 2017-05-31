package top.oahnus.controller.administrator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by oahnus on 2017/5/27
 * 0:10.
 */
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertCompanyByAdminUploadExcel() throws Exception {
        File file = new File("src/test/excel/company.xlsx");
        mockMvc.perform(
                fileUpload("/admin/companies")
                        .file(new MockMultipartFile(
                                "upload_file",
                                "company.xlsx",
                                "xlsx",
                                new FileInputStream(file)
                        ))
        ).andDo(print());
    }
    @Test
    public void insertTeacherByAdminUploadExcel() throws Exception {
        File file = new File("src/test/excel/teacher.xlsx");
        mockMvc.perform(
                fileUpload("/admin/teachers")
                        .file(new MockMultipartFile(
                                "upload_file",
                                "teacher.xlsx",
                                "xlsx",
                                new FileInputStream(file)
                        ))
        ).andDo(print());
    }
    @Test
    public void insertStudentByAdminUploadExcel() throws Exception {
        File file = new File("src/test/excel/student.xlsx");
        mockMvc.perform(
                fileUpload("/admin/students")
                        .file(new MockMultipartFile(
                                "upload_file",
                                "student.xlsx",
                                "xlsx",
                                new FileInputStream(file)
                        ))
        ).andDo(print());
    }
}