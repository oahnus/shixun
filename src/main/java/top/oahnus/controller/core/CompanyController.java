package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.controller.ServerState;
import top.oahnus.dto.CompanyDto;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.entity.Company;
import top.oahnus.service.CompanyService;

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

    @GetMapping("/{companyId}")
    public ResponseData<Company> selectCompanyById(@PathVariable("companyId")String companyId) {
        Company company = companyService.selectCompanyById(companyId);
        return new ResponseData<>(ServerState.SUCCESS, company, "success");
    }

    @GetMapping
    public ResponseData<Page> getAllCompany(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit) {
        Page<List<Company>> p = companyService.getAllCompany(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @PutMapping
    public ResponseData<Company> updateCompany(@Validated @RequestBody CompanyDto companyDto) {
        Company company = companyService.updateCompany(companyDto);
        return new ResponseData<>(ServerState.SUCCESS, company, "success");
    }

    @DeleteMapping("/{companyId}")
    public ResponseData<String> deleteCompanyById(@PathVariable String companyId) {
        companyService.deleteCompany(companyId);
        return new ResponseData<>(ServerState.SUCCESS, "success");
    }

    /**
     * 单条插入公司信息
     */
    @PostMapping
    public ResponseData<Company> insertNewCompany(@Validated @RequestBody CompanyDto companyDto) {
        Company company = companyService.addCompany(companyDto);
        return new ResponseData<>(ServerState.SUCCESS, company, "success");
    }
}
