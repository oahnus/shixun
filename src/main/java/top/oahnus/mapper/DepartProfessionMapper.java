package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by oahnus on 2017/4/22
 * 19:34.
 */
@Mapper
public interface DepartProfessionMapper {
    List<String> selectProfession();
    List<String> selectDepart();
}
