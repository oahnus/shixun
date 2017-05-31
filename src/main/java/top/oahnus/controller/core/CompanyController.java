package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.entity.Company;
import top.oahnus.enums.ServerState;
import top.oahnus.payload.CompanyPayload;
import top.oahnus.payload.ResponseData;
import top.oahnus.service.CompanyService;

/**
 * Created by oahnus on 2017/5/27
 * 12:07.
 */
@RestController
@CrossOrigin
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/{companyId}")
    public ResponseData<Company> getCompanyById(@PathVariable("companyId")Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        return new ResponseData<>(ServerState.SUCCESS, company, "success");
    }

    @GetMapping
    public ResponseData<Page> getCompanyByPage(
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit){
        Page<Company> pg = companyService.getCompanyByPage(page, limit);
        return new ResponseData<>(ServerState.SUCCESS, pg, "success");
    }

    @GetMapping("/name")
    public ResponseData<Page> getCompanyByNameLike(
            @RequestParam("name")String name,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit){
        Page<Company> companies = companyService.getCompanyByNameLike(name, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, companies, "success");
    }

    @PutMapping
    public ResponseData<Company> updateCompany(
            @Validated @RequestBody CompanyPayload companyPayload,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Company company = companyService.updateCompany(companyPayload);
        return new ResponseData<>(ServerState.SUCCESS, company, "success");
    }

    @DeleteMapping("/{companyId}")
    public ResponseData<String> deleteCompanyById(@PathVariable("companyId")Long companyId) {
        companyService.deleteCompany(companyId);
        return new ResponseData<>(ServerState.SUCCESS, "删除成功", "success");
    }

    @PostMapping
    public ResponseData<Company> insertCompany(
            @Validated @RequestBody CompanyPayload companyPayload,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseData<>(ServerState.REQUEST_PARAMETER_ERROR, result.getFieldError().getDefaultMessage());
        }
        Company company = companyService.insertCompany(companyPayload);
        return new ResponseData<>(ServerState.SUCCESS, company, "success");
    }
}
