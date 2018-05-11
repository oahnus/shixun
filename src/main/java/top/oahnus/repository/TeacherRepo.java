package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.Teacher;

/**
 * Created by oahnus on 2018/5/10
 * 22:35.
 */
@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Teacher findFirstByAuthId(Long authId);
}
