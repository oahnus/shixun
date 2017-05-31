package top.oahnus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Company;

import java.util.List;

/**
 * Created by oahnus on 2017/5/26
 * 21:28.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findFirstByName(String name);
    Page<Company> findByNameLike(String name, Pageable pageable);
}
