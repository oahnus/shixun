package top.oahnus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Teacher;

/**
 * Created by oahnus on 2017/5/26
 * 22:14.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findFirstByWorkerId(String workerId);
    Page<Teacher> findByNameLike(String name, Pageable pageable);
    Page<Teacher> findByWorkerIdLike(String workerId, Pageable pageable);
    Page<Teacher> findByDepartId(Long departId, Pageable pageable);
    Page<Teacher> findByProfessionId(Long professionId, Pageable pageable);
}
