package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.UserMenu;
import top.oahnus.enums.AuthType;

import java.util.List;

/**
 * Created by oahnus on 2017/4/7
 * 17:38.
 */
@Mapper
public interface UserMenuMapper {
    List<UserMenu> selectRootUserMenuByAuthType(@Param("authType")Integer type);
    List<UserMenu> selectChildUserMenuByParentId(@Param("parentId")Integer parentId);
}
