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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by oahnus on 2017/5/27
 * 13:39.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCompanyById() throws Exception {

    }

    @Test
    public void getCompanyByPage() throws Exception {
        mockMvc.perform(
                get("/companies")
                        .param("page", "2")
                        .param("limit", "2")
        ).andDo(print());
    }

    @Test
    public void getCompanyByNameLike() throws Exception {
        mockMvc.perform(
                get("/companies/name")
                        .param("name", "")
                        .param("page", "1")
                        .param("limit", "10")
        ).andDo(print());
    }

    @Test
    public void updateCompany() throws Exception {
        String json = "{\"id\":2,\"name\":\"南京紫金楼\",\"contact\":\"王某某\",\"contactPhone\":\"15751884919\",\"address\":\"江苏南京\",\"email\":\"47851541@163.com\"}";
        mockMvc.perform(put("/companies")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @Test
    @Transactional
    public void deleteCompany() throws Exception {
        mockMvc.perform(delete("/companies/2")).andDo(print());
    }
}