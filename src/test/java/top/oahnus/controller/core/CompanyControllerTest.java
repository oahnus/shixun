package top.oahnus.controller.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by oahnus on 2017/3/23 21:12.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
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
}