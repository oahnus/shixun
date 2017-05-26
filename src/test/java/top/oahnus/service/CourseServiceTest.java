package top.oahnus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.dto.Page;
import top.oahnus.enums.CourseState;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by oahnus on 2017/5/9
 * 21:22.
 */

@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;


    @Test
    public void testGetCourse() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Page page = courseService.selectAllCourse(CourseState.COURSE_START, 1,100);
        }
        System.out.print("耗时");
        System.out.println(System.currentTimeMillis() - start);
    }
}
