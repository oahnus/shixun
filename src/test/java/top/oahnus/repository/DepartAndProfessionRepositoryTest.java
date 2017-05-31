package top.oahnus.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.entity.Depart;
import top.oahnus.entity.Profession;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/5/28
 * 18:01.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartAndProfessionRepositoryTest {
    @Autowired
    private DepartRepository departRepository;
    @Autowired
    private ProfessionRepository professionRepository;

    @Test
    public void getAll() {
        List<Depart> departList = departRepository.findAll();
        departList.forEach(System.out::println);
    }

    @Test
    public void getProfession() {
        List<Profession> professions = professionRepository.findAll();
        professions.forEach(System.out::println);
    }

    @Test
    public void saveProfession() {
        Profession profession = new Profession();
    }
}