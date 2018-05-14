package top.oahnus.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.oahnus.common.payload.pageForm.SelectionRecordForm;
import top.oahnus.domain.SelectionRecord;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/**
 * Created by oahnus on 2018/5/14
 * 11:24.
 */
@Repository
public interface SelectionRecordRepo extends JpaRepository<SelectionRecord, Long>, JpaSpecificationExecutor<SelectionRecord> {
    default Page<SelectionRecord> findByForm(SelectionRecordForm form) {
        Specification<SelectionRecord> specification = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();

            Long studentId = form.getStudentId();
            Long courseId = form.getCourseId();

//            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime")));
            predicates.add(criteriaBuilder.equal(root.get("delFlag"), false));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return findAll(specification, form.getPage());
    }
}
