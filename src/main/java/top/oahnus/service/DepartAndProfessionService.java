package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.oahnus.domain.Department;
import top.oahnus.domain.Profession;
import top.oahnus.repository.DepartmentRepo;
import top.oahnus.repository.ProfessionRepo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<Department> findAllDepartWithProfession() {
        List<Department> departList = findAllDepart();
        List<Profession> professionList = findAllProfession();
        Map<Long, List<Profession>> proMap = professionList
                .stream()
                .collect(Collectors.groupingBy(Profession::getDepartId));

        departList.forEach(depart -> depart.setProfessions(proMap.get(depart.getId())));
        return departList;
    }

//    @Cacheable("day")
    public List<Department> findAllDepart() {
        return departmentRepo.findByDelFlagFalse();
    }

//    @Cacheable("day")
    public List<Profession> findAllProfession() {
        return professionRepo.findByDelFlagFalse();
    }
}
