package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Admin;
import top.oahnus.mymapper.MyMapper;

/**
 * Created by oahnus on 2018/5/9
 * 17:28.
 */
@Mapper
@Repository
public interface AdminMapper extends MyMapper<Admin> {
    Admin findFirstByAuthId(Long authId);
}
