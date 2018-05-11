package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.Company;

/**
 * Created by oahnus on 2018/5/10
 * 23:36.
 */
@Repository
public interface CompanyRepo extends JpaRepository<Company, Long>{
    Company findFirstByAuthId(Long authId);
}
