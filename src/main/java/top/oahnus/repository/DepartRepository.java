package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Depart;

/**
 * Created by oahnus on 2017/5/28
 * 18:00.
 */
@Repository
public interface DepartRepository extends JpaRepository<Depart, Long> {
}
