package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.CompanyDto;
import top.oahnus.entity.Company;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.SQLExecuteExceeption;
import top.oahnus.mapper.CompanyMapper;

import java.util.List;

/**
 * Created by oahnus on 2017/3/23 20:41.
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> getAllCompany(Integer page, Integer limit) {
        if(page == null || limit == null) {
            throw new BadRequestParamException("请求参数错误");
        }
        return companyMapper.selectAllCompany((page - 1) * limit, limit);
    }

    @Override
    public Company addCompany(CompanyDto companyDto) {
        Company company = new Company(
                companyDto.getName(),
                companyDto.getContact(),
                companyDto.getContactPhone(),
                companyDto.getAddress(),
                companyDto.getEmail()
        );
        Integer count = companyMapper.insertIntoCompany(company);
        System.out.println(company.getId());
        if (count > 0) {
            return company;
        } else {
            throw new SQLExecuteExceeption("数据库存储失败");
        }
    }
}
