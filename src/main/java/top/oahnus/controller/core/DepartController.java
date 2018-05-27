package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oahnus.common.dto.ResultData;
import top.oahnus.domain.Department;
import top.oahnus.service.DepartAndProfessionService;

import java.util.List;

/**
 * Created by oahnus on 2018/5/27
 * 0:31.
 */
@RestController
@CrossOrigin
@RequestMapping("/depart")
public class DepartController {
    @Autowired
    private DepartAndProfessionService dapService;

    @GetMapping
    public ResultData fetchAllDepart() {
        List<Department> departList = dapService.findAllDepartWithProfession();
        return new ResultData().data("departList", departList);
    }
}
