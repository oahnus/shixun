package top.oahnus.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.oahnus.common.payload.pageForm.CompanyPageForm;
import top.oahnus.domain.Company;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2018/5/10
 * 23:36.
 */
@Repository
public interface CompanyRepo extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    Company findFirstByAuthIdAndDelFlagFalse(Long authId);
    List<Company> findByNameAndDelFlagFalse(String name);

    default Page<Company> findByForm(CompanyPageForm form) {
        Specification<Company> specification = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            String name = form.getName();
            String address = form.getAddress();
            if (StringUtils.isNotBlank(name)) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + name + "%"),
                        criteriaBuilder.like(root.get("address"), "%" + name + "%")
                ));
            }
//            if (StringUtils.isNotBlank(address)) {
//                predicates.add(criteriaBuilder.like(root.get("address"), "%" + address + "%"));
//            }

            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime")));
            predicates.add(criteriaBuilder.equal(root.get("delFlag"), false));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return findAll(specification, form.getPage());
    }
}
