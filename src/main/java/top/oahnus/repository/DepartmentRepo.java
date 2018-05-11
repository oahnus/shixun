package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.Department;

import java.util.List;

/**
 * Created by oahnus on 2018/5/10
 * 22:50.
 */
@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    List<Department> findByDelFlagFalse();
}
