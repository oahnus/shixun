package top.oahnus.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.oahnus.common.payload.pageForm.TeacherPageForm;
import top.oahnus.domain.Teacher;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/**
 * Created by oahnus on 2018/5/10
 * 22:35.
 */
@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    Teacher findFirstByAuthId(Long authId);

    default Page<Teacher> findByForm(TeacherPageForm form) {
        Specification<Teacher> specification = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            String name = form.getName();
            String workerId = form.getWorkerId();
            Long departId = form.getDepartId();
            Long professionId = form.getProfessionId();

            if (StringUtils.isNotBlank(name)) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + name + "%"),
                        criteriaBuilder.like(root.get("workerId"), "" + workerId + "%")
                ));
            }
//            if (StringUtils.isNotBlank(workerId)) {
//                predicates.add(criteriaBuilder.equal(root.get("workerId"), workerId));
//            }
            if (departId != null) {
                predicates.add(criteriaBuilder.equal(root.get("department.id"), departId));
            }
            if (professionId != null) {
                predicates.add(criteriaBuilder.equal(root.get("profession.id"), professionId));
            }
//            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime")));
            predicates.add(criteriaBuilder.equal(root.get("delFlag"), false));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return findAll(specification, form.getPage());
    }
}
