package top.oahnus.service;

import top.oahnus.dto.CompanyDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Company;

import java.util.List;

/**
 * Created by oahnus on 2017/3/23
 * 20:40.
 */
public interface CompanyService {
    Page<List<Company>> getAllCompany(Integer page, Integer limit);
    Company selectCompanyById(String companyId);

    Company addCompany(CompanyDto companyDto);
    Company updateCompany(CompanyDto companyDto);
    Integer deleteCompany(String companyId);

    List<Company> insertCompanies(List<Company> companies);
}
