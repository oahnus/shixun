package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Profession;

/**
 * Created by oahnus on 2017/5/28
 * 18:06.
 */
@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {

}
