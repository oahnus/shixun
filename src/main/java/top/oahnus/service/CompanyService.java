package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.CompanyDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Company;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.exception.ReadDataFailedException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.CompanyMapper;
import top.oahnus.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2017/3/23 
 * 20:41.
 */
@Service
public class CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    public Page<List<Company>> getAllCompany(Integer page, Integer limit) {
        if(page == null || limit == null) {
            throw new BadRequestParamException("请求参数错误");
        }
        List<Company> companies = companyMapper.selectAllCompany((page - 1) * limit, limit);
        Integer totalRecord = companyMapper.selectRecordCount(null);
        return new Page<>(companies, totalRecord, page, limit);
    }

    public Company selectCompanyById(String companyId) {
        if (StringUtil.isEmpty(companyId)) {
            throw new BadRequestParamException("请求参数错误");
        }
        Company company = companyMapper.selectCompanyById(companyId);
        if (company == null) {
            throw new NotFoundException("数据未找到");
        }
        return company;
    }

    public Company addCompany(CompanyDto companyDto) {
        Company company = new Company(companyDto);
        Integer count = companyMapper.insertOneCompany(company);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据库失败");
        } else {
            company = companyMapper.selectCompanyByName(companyDto.getName());
            return company;
        }
    }

    // TODO 更新操作返回Bean？此处返回的company id为null
    public Company updateCompany(CompanyDto companyDto) {
        Company company = new Company(companyDto);
        if (company.getId() == null) {
            throw new BadRequestParamException("id不能为空");
        }
        Integer count = companyMapper.updateCompany(company);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("更新数据库失败");
        } else {
            company = companyMapper.selectCompanyById(companyDto.getId());
            return company;
        }
    }

    public Integer deleteCompany(String companyId) {
        if (StringUtil.isEmpty(companyId)) {
            throw new BadRequestParamException("请求参数错误");
        }
        Integer count = companyMapper.deleteCompanyById(companyId);
        if (count > 0) {
            return count;
        } else {
            throw new SQLExecuteFailedExceeption("删除数据库失败");
        }
    }

    public List<Company> insertCompanies(List<Company> companies) {
        if (companies == null) {
            throw new ReadDataFailedException("从Excel中读取数据失败");
        }
        List<Company> companyList = new ArrayList<>();
        // java8 for each
        Integer count = companyMapper.insertCompanies(companies);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据库失败");
        } else {
            companies.forEach(company -> {
                companyList.add(companyMapper.selectCompanyByName(company.getName()));
            });
        }
        return companyList;
    }
}
