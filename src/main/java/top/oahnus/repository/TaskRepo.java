package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.Task;

import java.util.List;

/**
 * Created by oahnus on 2018/5/14
 * 10:02.
 */
@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByCourseIdAndDelFlagFalse(Long courseId);
    List<Task> findByCourseIdAndCreateUserIdAndDelFlagFalse(Long courseId, Long createUserId);
}