package top.oahnus.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.common.payload.pageForm.CoursePageForm;
import top.oahnus.domain.Company;
import top.oahnus.domain.Course;
import top.oahnus.domain.Teacher;
import top.oahnus.enums.CourseState;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2018/5/13
 * 21:20.
 */
@Repository
public interface CourseRepo extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE course SET state = :state WHERE del_flag = 0 AND id IN :courseIds", nativeQuery = true)
    Integer switchStateWithCourseIdIn(@Param("courseIds")List<Long> courseIds,
                                      @Param("state") CourseState state);

    default Page<Course> findByForm(CoursePageForm form) {
        Specification<Course> specification = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            String name = form.getName();
            Long professionId = form.getProfessionId();
            Long teacherId = form.getTeacherId();
            Long companyId = form.getCompanyId();

            CourseState state = form.getState();

            if (StringUtils.isNotBlank(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), name));
            }
            if (professionId != null) {
                String proId = String.format("[%d]", form.getProfessionId());
                predicates.add(criteriaBuilder.like(root.get("professionIds"), proId));
            }
            if (teacherId != null) {
                predicates.add(criteriaBuilder.equal(root.get("teacher").get("id"), teacherId));
            }
            if (companyId != null) {
                predicates.add(criteriaBuilder.equal(root.get("company").get("id"), companyId));
            }
            if (state != null) {
                predicates.add(criteriaBuilder.equal(root.get("state"), state));
            }
            predicates.add(criteriaBuilder.equal(root.get("delFlag"), false));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return findAll(specification, form.getPage());
    }
}
