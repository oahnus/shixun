package top.oahnus.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.entity.Course;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/5/28
 * 16:56.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getCourse() {
        List<Course> courseList = courseRepository.findAll();
        courseList.forEach(System.out::println);
    }

}