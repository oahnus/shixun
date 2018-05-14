package top.oahnus.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.oahnus.common.payload.pageForm.StudentPageForm;
import top.oahnus.domain.Student;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/**
 * Created by oahnus on 2018/5/10
 * 23:41.
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    Student findFirstByAuthId(Long authId);

    default Page<Student> findByForm(StudentPageForm form) {
        Specification<Student> specification = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            String name = form.getName();
            String stuNumber = form.getStuNumber();
            Long departId = form.getDepartId();
            Long professionId = form.getProfessionId();

            if (StringUtils.isNotBlank(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), name));
            }
            if (StringUtils.isNotBlank(stuNumber)) {
                predicates.add(criteriaBuilder.equal(root.get("stuNumber"), stuNumber));
            }
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
