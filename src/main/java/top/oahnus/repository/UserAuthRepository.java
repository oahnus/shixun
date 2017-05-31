package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.UserAuth;
import top.oahnus.enums.AuthType;

/**
 * Created by oahnus on 2017/5/26
 * 14:38.
 */
@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByUsernameAndPasswordAndType(String username, String password, AuthType type);

    @Modifying
    @Query("update user_auth set password = ?3 where username = ?1 and password = ?2 and type = ?4")
    Integer resetPassword(String username, String password, String newPassword, AuthType type);
}
