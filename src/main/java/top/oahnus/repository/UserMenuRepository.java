package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.UserMenu;
import top.oahnus.enums.AuthType;

import java.util.List;

/**
 * Created by oahnus on 2017/5/26
 * 21:21.
 */
@Repository
public interface UserMenuRepository extends JpaRepository<UserMenu, Long> {
    List<UserMenu> findByAuthTypeAndParentId(AuthType authType, Long parentId);
    List<UserMenu> findByParentId(Long parentId);
}
