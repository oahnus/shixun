package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.oahnus.domain.Department;
import top.oahnus.domain.Profession;
import top.oahnus.repository.DepartmentRepo;
import top.oahnus.repository.ProfessionRepo;

import java.util.List;
import java.util.Optional;

/**
 * Created by oahnus on 2018/5/10
 * 16:17.
 */
@Service
public class DepartAndProfessionService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ProfessionRepo professionRepo;

    public Department getDepartByName(String name) {
        return findAllDepart().stream()
                .filter(depart -> depart.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Profession getProByName(String name){
        return findAllProfession().stream()
                .filter(profession -> profession.getName().equals(name))
                .findFirst()
                .orElse(null);

    }

    @Cacheable("day")
    public List<Department> findAllDepart() {
        return departmentRepo.findByDelFlagFalse();
    }

    @Cacheable("day")
    public List<Profession> findAllProfession() {
        return professionRepo.findByDelFlagFalse();
    }
}
