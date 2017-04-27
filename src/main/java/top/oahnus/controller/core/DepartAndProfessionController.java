package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oahnus.enums.ServerState;
import top.oahnus.dto.ResponseData;
import top.oahnus.mapper.DepartProfessionMapper;
import top.oahnus.service.DepartAndProfessionService;

import java.util.List;

/**
 * Created by oahnus on 2017/4/22
 * 19:24.
 */
@RestController
@CrossOrigin
public class DepartAndProfessionController {
    @Autowired
    private DepartAndProfessionService departAndProfessionService;

    @GetMapping("/professions")
    public ResponseData<List<String>> getAllProfession(){
        List<String> professions = departAndProfessionService.getAllProfession();
        return new ResponseData<>(ServerState.SUCCESS, professions, "success");
    }

    @GetMapping("/departs")
    public ResponseData<List<String>> getAllDepart() {
        List<String> departs = departAndProfessionService.getAllDepart();
        return new ResponseData<>(ServerState.SUCCESS, departs, "success");
    }
}
