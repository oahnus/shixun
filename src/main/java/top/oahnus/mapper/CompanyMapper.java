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

    Integer insertIntoCompany(Company company);

    Integer updateCompany(Company company);

    Integer deleteCompanyById(@Param("id") String id);
}
