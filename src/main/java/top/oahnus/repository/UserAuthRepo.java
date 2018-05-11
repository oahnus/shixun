package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.UserAuth;
import top.oahnus.enums.RoleEnum;

import java.util.List;

/**
 * Created by oahnus on 2018/5/10
 * 22:28.
 */
@Repository
public interface UserAuthRepo extends JpaRepository<UserAuth, Long> {
    UserAuth findFirstByUsername(String username);
    List<UserAuth> findByRoleAndUsernameIn(RoleEnum role, List<String> usernameList);
}
