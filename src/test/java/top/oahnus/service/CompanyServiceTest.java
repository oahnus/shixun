package top.oahnus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.dto.Page;
import top.oahnus.entity.Company;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/3/23
 * 20:42.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Test
    public void getAllCompany() throws Exception {
        Page<List<Company>> page = companyService.getAllCompany(1, 2);
        page.getContent().forEach(System.out::println);
    }

}