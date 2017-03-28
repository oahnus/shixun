package top.oahnus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/3/28
 * 18:42.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherServiceImplTest {
    @Autowired
    private TeacherService teacherService;

    @Test
    public void selectTeacherByProfession() throws Exception {
        teacherService.selectTeacherByProfession("通信工程",1, 10)
                .forEach(System.out::println);
    }

    @Test
    public void selectTeacherByDepart() throws Exception {

    }

    @Test
    public void insertIntoTeacher() throws Exception {

    }

    @Test
    public void deleteTeacherById() throws Exception {

    }

    @Test
    public void updateTeacher() throws Exception {

    }

}