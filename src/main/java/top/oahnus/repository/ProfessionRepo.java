package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.Profession;

import java.util.List;

/**
 * Created by oahnus on 2018/5/10
 * 22:49.
 */
@Repository
public interface ProfessionRepo extends JpaRepository<Profession, Long> {
    List<Profession> findByDelFlagFalse();
}
