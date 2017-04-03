package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.Company;

import java.util.List;

/**
 * Created by oahnus on 2017/3/16.
 * 1:07
 */
@Mapper
public interface CompanyMapper {
    List<Company> selectAllCompany(@Param("offset")Integer offset, @Param("limit")Integer limit);
    Company selectCompanyByName(@Param("name") String name);
    Company selectCompanyById(@Param("id")String id);
    //TODO 分页查询，同事返回数据总数
    Integer selectCountOfCompany();

    Integer insertOneCompany(Company company);
    Integer insertCompanies(List<Company> companies);

    Integer updateCompany(Company company);

    Integer deleteCompanyById(@Param("id") String id);
}
