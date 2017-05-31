package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.oahnus.entity.Company;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.payload.CompanyPayload;
import top.oahnus.repository.CompanyRepository;

/**
 * Created by oahnus on 2017/5/27
 * 12:08.
 */
@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompanyById(Long id) {
        Company company = companyRepository.findOne(id);
        if (company == null) throw new NotFoundException("未找到公司信息");
        return company;
    }

    public Page<Company> getCompanyByPage(Integer page, Integer limit){
        Pageable pageable = new PageRequest(page - 1, limit);

        Page<Company> companies = companyRepository.findAll(pageable);
        if (companies == null) throw new NotFoundException("未找到公司信息");
        return companies;
    }

    public Page<Company> getCompanyByNameLike(String name, Integer page, Integer limit) {
        name = "%" + (name == null ? "" : name) + "%";
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Company> companies = companyRepository.findByNameLike(name, pageable);
        if (companies == null) throw new NotFoundException("未找到公司信息");
        return companies;
    }

    public Company updateCompany(CompanyPayload companyPayload) {
        if (companyPayload.getId() == null) throw new BadRequestParamException("缺失参数Id");
        Company company = Company.fromPayload(companyPayload);
        company = companyRepository.save(company);
        return company;
    }

    public void deleteCompany(Long companyId) {
        if (companyId == null) {
            throw new BadRequestParamException("公司Id为空");
        }
        companyRepository.delete(companyId);
    }

    public Company insertCompany(CompanyPayload companyPayload) {
        Company company = Company.fromPayload(companyPayload);
        company = companyRepository.save(company);
        return company;
    }
}
