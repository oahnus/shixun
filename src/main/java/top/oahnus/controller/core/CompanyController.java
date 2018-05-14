package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.annotations.NeedAdmin;
import top.oahnus.common.dto.ResultData;
import top.oahnus.common.interfaces.HttpMixin;
import top.oahnus.common.payload.pageForm.CompanyPageForm;
import top.oahnus.domain.Company;
import top.oahnus.repository.CompanyRepo;
import top.oahnus.service.CompanyService;

import javax.validation.Valid;

/**
 * Created by oahnus on 2017/2/26.
 * 21:31
 */
@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController implements HttpMixin{
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepo companyRepo;


    @PostMapping("/page")
    public ResultData listByPage(@RequestBody CompanyPageForm form) {
        Page<Company> page = companyRepo.findByForm(form);
        return new ResultData().data("page", page);
    }

    @NeedAdmin
    @PostMapping
    public ResultData insertCompany(@RequestBody @Valid Company company) {
        companyService.save(company);
        return new ResultData();
    }

    @PutMapping
    public ResultData updateCompany(@RequestBody @Valid Company company) {
        companyService.update(company);
        return new ResultData();
    }

    @DeleteMapping("/{id}")
    public ResultData deleteById(@PathVariable("id")Long companyId) {
        companyService.delete(companyId);
        return new ResultData();
    }
}
