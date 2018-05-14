package top.oahnus.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.common.payload.pageForm.CoursePageForm;
import top.oahnus.common.payload.pageForm.PageForm;
import top.oahnus.domain.Course;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2018/5/14
 * 11:34.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseRepoTest {
    @Autowired
    private CourseRepo courseRepo;

    @Test
    public void test() {
        CoursePageForm form = new CoursePageForm();
        form.setTeacherId(2L);
        form.setPage(new PageForm(0, 10));
        Page<Course> page = courseRepo.findByForm(form);
        System.out.println(page.getContent());
    }
}