package top.oahnus.controller.core;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.oahnus.Constants;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.CompanyDto;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.Company;
import top.oahnus.service.CompanyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 21:31
 */
@CrossOrigin
@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseData<Company> insertNewCompany(@RequestBody CompanyDto companyDto) {
        Company company = companyService.addCompany(companyDto);
        return new ResponseData<>(ServerState.SUCCESS, company, "success");
    }

    @GetMapping
    public ResponseData<List<Company>> getAllCompany(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit) {
        List<Company> companies = companyService.getAllCompany(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, companies, "success");
    }
}
