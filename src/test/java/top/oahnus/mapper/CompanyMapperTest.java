package top.oahnus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/3/31
 * 21:16.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyMapperTest {
    @Autowired
    private CompanyMapper companyMapper;

    @Test
    public void selectCountOfCompany() throws Exception {
        Integer count = companyMapper.selectCountOfCompany();
        System.out.println(count);
    }

}