package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.Contact;

import java.util.List;

/**
 * Created by oahnus on 2018/5/11
 * 14:42.
 */
@Repository
public interface ContactRepo extends JpaRepository<Contact, Long>{
    List<Contact> findByCompanyId(Long companyId);
}
