package top.oahnus.controller.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by oahnus on 2017/3/23 21:12.
 * CompanyController Test
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddCompany() throws Exception {
        String json = "{\"name\":\"观海楼大酒店\",\"contact\":\"周长生\",\"contactPhone\":\"13945689569\",\"address\":\"江苏省镇江市梦溪路2号\"}";
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
        ).andDo(print());
    }

    @Test
    public void getAll() throws Exception {
//        String json = "{\"name\":\"观海楼大酒店\",\"contact\":\"周长生\",\"contactPhone\":\"13945689569\",\"address\":\"江苏省镇江市梦溪路2号\"}";
        mockMvc.perform(get("/companies")
                .param("page","1")
                .param("limit","2")
                .characterEncoding("utf-8")
        ).andDo(print());
    }

    @Test
    public void update() throws Exception {
        String json = "{\"id\":\"7247af1f11ca11e7adce80fa5b3ea16e\",\"name\":\"观海楼大酒店\",\"contact\":\"郭宇轩\",\"contactPhone\":\"13945689569\",\"address\":\"江苏省镇江市梦溪路2号\"}";
        mockMvc.perform(put("/companies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
        ).andDo(print());
    }

    @Test
    public void deleteName() throws Exception {
        mockMvc.perform(delete("/companies/7247af1f11ca11e7adce80fa5b3ea16e")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print());
    }
}