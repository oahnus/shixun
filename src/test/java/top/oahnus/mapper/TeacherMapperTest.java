package top.oahnus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/4/8
 * 9:52.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherMapperTest {
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void selectCount(){
        System.out.println(teacherMapper.selectRecordCount(null));
    }
}