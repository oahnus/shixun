package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.Admin;

/**
 * Created by oahnus on 2018/5/10
 * 23:40.
 */
@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findFirstByAuthId(Long authId);
}
