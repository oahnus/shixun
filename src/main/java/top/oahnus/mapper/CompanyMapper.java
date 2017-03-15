package top.oahnus.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import top.oahnus.entity.Company;

/**
 * Created by oahnus on 2017/3/16.
 * 1:07
 */
@Mapper
public interface CompanyMapper {
    @Insert("INSERT INGORE into company (name, contact, address) VALUES (#{name}, #{contact}, #{address})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertCompany(Company company);
}
