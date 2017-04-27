package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import top.oahnus.dto.ResponseData;
import top.oahnus.enums.ServerState;
import top.oahnus.mapper.DepartProfessionMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2017/4/27
 * 22:31.
 */
@Service
public class DepartAndProfessionService {
    @Autowired
    private DepartProfessionMapper departProfessionMapper;

    @Cacheable(value = "professionscache", keyGenerator = "myKeyGenerator")
    public List<String> getAllProfession(){
        return departProfessionMapper.selectProfession();
    }

    @Cacheable(value = "departscache", keyGenerator = "myKeyGenerator")
    public List<String> getAllDepart() {
        return departProfessionMapper.selectDepart();
    }
}
