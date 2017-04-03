package top.oahnus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.entity.Company;
import top.oahnus.entity.Course;
import top.oahnus.entity.Teacher;
import top.oahnus.enums.CourseState;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/4/3
 * 14:16.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseMapperTest {
    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void testGetAll(){
        List<Course> courseList = courseMapper.selectAllCourse(0,10);
        courseList.forEach(System.out::println);
    }

    @Test
    public void testSelectCourseByProfessionsLikeProfession() {
        List<Course> courses = courseMapper.selectCourseByProfessionsLikeProfession("通信工程", 0, 10);
        courses.forEach(System.out::println);
    }

    @Test
    public void testSelectCourseByTeacherId() {
        List<Course> courses = courseMapper.selectCourseByTeacherId("33bbb073141d11e7becf80fa5b3ea16e", 0, 10);
        courses.forEach(System.out::println);
    }

    @Test
    public void testSelectCourseByCompanyId() {
        List<Course> courses = courseMapper.selectCourseByCompanyId("33ba6d80141d11e7becf80fa5b3ea16e", 0, 10);
        courses.forEach(System.out::println);
    }

    @Test
    public void testSelectCountCourse() {
        Integer count = courseMapper.selectCountCourseByCondition(null,null,null);
        System.out.println(count);
    }

    @Test
    public void testInsertNewCourse() {
        Course course = new Course();
        Teacher teacher = new Teacher();
        Company company = new Company();
        teacher.setId("33bbb073141d11e7becf80fa5b3ea16e");
        company.setId("33ba6d80141d11e7becf80fa5b3ea16e");

        course.setName("测试课程");
        course.setProfessions("通信工程;测试专业");
        course.setTeacher(teacher);
        course.setCompany(company);
        course.setAddition("附件地址");
        course.setStartTime(new Date());
        course.setEndTime(new Date());
        course.setMemo("");
        course.setState(CourseState.ON_SELECTED);

        Integer count = courseMapper.insertNewCourse(course);
        System.out.println(count);
    }
}