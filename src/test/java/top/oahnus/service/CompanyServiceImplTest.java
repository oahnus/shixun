package top.oahnus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.entity.Company;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/3/23 20:42.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyServiceImplTest {
    @Autowired
    private CompanyService companyService;

    @Test
    public void getAllCompany() throws Exception {
        List<Company> companies = companyService.getAllCompany(1, 2);
        Iterator<Company> iterator = companies.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}