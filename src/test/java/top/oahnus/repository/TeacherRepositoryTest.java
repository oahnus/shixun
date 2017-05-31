package top.oahnus.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.entity.Depart;
import top.oahnus.entity.Profession;
import top.oahnus.entity.Teacher;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/5/28
 * 19:04.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void findByDepart() throws Exception {
        Pageable pageable = new PageRequest(0 ,10);

        Page<Teacher> p = teacherRepository.findByDepartId(1L, new PageRequest(0, 10));
        p.getContent().forEach(System.out::println);
    }

    @Test
    public void saveTeacher() {
//        Teacher teacher = new Teacher();
//        teacher.setName("测试教师");
//        teacher.setSex("女");
//        teacher.setWorkerId("1333122");
//        teacher.setJobTitle("教师");
//        teacher.setDepartId("1");
//        teacher.setProfessionId("1");
//        teacher = teacherRepository.saveAndFlush(teacher);
//        System.out.println(teacher);
    }
}