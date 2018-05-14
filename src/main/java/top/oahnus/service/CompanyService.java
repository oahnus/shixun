package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.domain.Company;
import top.oahnus.domain.UserAuth;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.repository.CompanyRepo;
import top.oahnus.repository.UserAuthRepo;

import java.util.Date;

/**
 * Created by oahnus on 2017/3/23 
 * 20:41.
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private UserAuthRepo authRepo;

    @Transactional
    public void save(Company company) {
        String name = company.getName();
        String email = company.getEmail();

        checkExisted(name, email);

        UserAuth auth;
        auth = UserAuth.buildByEmail(email);
        auth = authRepo.save(auth);
        company.setAuthId(auth.getId());
        companyRepo.save(company);
    }

    public void update(Company company) {
        String name = company.getName();
        String email = company.getEmail();
        checkExisted(name, email);

        company.setUpdateTime(new Date());

        companyRepo.save(company);
    }

    public void delete(Long companyId) {
        Company company = companyRepo.findOne(companyId);
        company.setDelFlag(true);
        companyRepo.save(company);
    }

    private void checkExisted(String name, String email) {
        UserAuth auth = authRepo.findFirstByUsername(email);
        if (auth != null) {
            throw new DataExistedException("");
        }
        Company c = companyRepo.findFirstByNameAndDelFlagFalse(name);
        if (c != null) {
            throw new DataExistedException("");
        }
    }
}
