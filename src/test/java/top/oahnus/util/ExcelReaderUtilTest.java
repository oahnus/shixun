package top.oahnus.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.config.ExcelReaderConfig;
import top.oahnus.entity.Company;
import top.oahnus.entity.Teacher;
import top.oahnus.enums.AuthType;

import java.io.File;
import java.util.List;

/**
 * Created by oahnus on 2017/3/26
 * 17:04.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ExcelReaderUtilTest {

    @Autowired
    private ExcelReaderConfig config;

    @Test
    public void TestGetValue(){
        System.out.println(config.getCompany().getName());
        System.out.println(config.getStudent());
        System.out.println(config.getTeacher());
    }

    @Test
    public void ReadExcel() {
        File file = new File("src/test/excel/company.xlsx");
        if (file.exists()) {
            List<Company> companies = ExcelReaderUtil.readExcelFile(file, AuthType.COMPANY);
            if (companies != null) {
                companies.forEach(System.out::println);
            }
        } else {
            System.out.println("file not found");
        }
    }

    @Test
    public void ReadTeacherExcel() {
        File file = new File("src/test/excel/teacher.xlsx");
        if (file.exists()) {
            List<Teacher> teachers = ExcelReaderUtil.readExcelFile(file, AuthType.TEACHER);
            System.out.println(teachers.size());
            if (teachers != null) {
                teachers.forEach(System.out::println);
            }
        } else {
            System.out.println("file not found");
        }
    }
}