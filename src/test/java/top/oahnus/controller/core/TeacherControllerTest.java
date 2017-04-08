package top.oahnus.controller.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by oahnus on 2017/4/8
 * 16:14.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTeacher() throws Exception {

    }

    @Test
    public void getTeacherByProfession() throws Exception {

    }

    @Test
    public void getTeacherByDepart() throws Exception {

    }

    @Test
    public void insertOneTeacher() throws Exception {
        String json = "{\"workerId\":\"130025\",\"name\":\"周长生\",\"sex\":\"男\",\"jobTitle\":\"教师\",\"profession\":\"计算机专业\",\"depart\":\"计算机学院\"}";
        mockMvc.perform(post("/teachers")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
        ).andDo(print());
    }

    @Test
    public void updateTeacher() throws Exception {

    }

    @Test
    public void deleteTeacherById() throws Exception {

    }

}