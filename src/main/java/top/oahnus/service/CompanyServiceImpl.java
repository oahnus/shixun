package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.entity.Company;
import top.oahnus.mapper.CompanyMapper;

import java.util.List;

/**
 * Created by oahnus on 2017/2/28.
 * 1:08
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> insertCompanyByBatch(List<Company> companies) {
        for(Company company: companies){
            companyMapper.insertCompany(company);
        }
        return companies;
    }
}
