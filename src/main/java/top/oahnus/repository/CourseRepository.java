package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Course;

/**
 * Created by oahnus on 2017/5/28
 * 16:56.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
