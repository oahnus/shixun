package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.UserMenu;

import java.util.List;

/**
 * Created by oahnus on 2018/5/24
 * 14:40.
 */
@Repository
public interface UserMenuRepo extends JpaRepository<UserMenu, Long> {
    List<UserMenu> findByRoleIdAndDelFlagFalse(Integer roleId);
}
