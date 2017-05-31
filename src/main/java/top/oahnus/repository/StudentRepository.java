package top.oahnus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Student;

/**
 * Created by oahnus on 2017/5/26
 * 22:16.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findFirstByStudentNum(String studentNum);
    Page<Student> findByNameLike(String name, Pageable pageable);
    Page<Student> findByStudentNumLike(String studentNum, Pageable pageable);
    Page<Student> findByDepartId(Long departId, Pageable pageable);
    Page<Student> findByProfessionId(Long professionId, Pageable pageable);
}
