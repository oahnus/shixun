package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.CompanyDto;
import top.oahnus.entity.Company;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.SQLExecuteExceeption;
import top.oahnus.mapper.CompanyMapper;
import top.oahnus.util.StringUtil;

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
        System.out.println(company);
        Integer count = companyMapper.insertIntoCompany(company);
        if (count < 0) {
            throw new SQLExecuteExceeption("插入数据库失败");
        } else {
            company = companyMapper.selectCompanyByName(companyDto.getName());
            return company;
        }
    }

    // TODO 更新操作返回Bean？此处返回的company id为null
    @Override
    public Company updateCompany(CompanyDto companyDto) {
        Company company = new Company(
                companyDto.getName(),
                companyDto.getContact(),
                companyDto.getContactPhone(),
                companyDto.getAddress(),
                companyDto.getEmail()
        );
        Integer count = companyMapper.updateCompany(company);
        if (count < 0) {
            throw new SQLExecuteExceeption("更新数据库失败");
        } else {
            return company;
        }
    }

    @Override
    public Integer deleteCompany(String companyId) {
        if (StringUtil.isEmpty(companyId)) {
            throw new BadRequestParamException("请求参数错误");
        }
        Integer count = companyMapper.deleteCompanyById(companyId);
        if (count > 0) {
            return count;
        } else {
            throw new SQLExecuteExceeption("删除数据库失败");
        }
    }
}
