package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.Student;

/**
 * Created by oahnus on 2018/5/10
 * 23:41.
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findFirstByAuthId(Long authId);
}
