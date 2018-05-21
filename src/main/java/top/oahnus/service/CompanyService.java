package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.domain.Company;
import top.oahnus.domain.Contact;
import top.oahnus.domain.UserAuth;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.repository.CompanyRepo;
import top.oahnus.repository.ContactRepo;
import top.oahnus.repository.UserAuthRepo;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private ContactRepo contactRepo;

    @Transactional
    public void save(Company company) {
        String email = company.getEmail();

        checkExistedComp(company);

        UserAuth auth;
        auth = UserAuth.buildByEmail(email);
        auth = authRepo.save(auth);
        company.setAuthId(auth.getId());
        company.setCreateTime(new Date());
        company.setUpdateTime(new Date());
        companyRepo.save(company);
    }

    public void update(Company company) {
        Long companyId = company.getId();
        Company comp = companyRepo.findOne(companyId);
        if (comp == null) {
            throw new NotFoundException("");
        }
        checkExistedComp(company);

        comp.setName(company.getName());
        comp.setAddress(company.getAddress());
        comp.setTelephone(company.getTelephone());
        comp.setContactList(company.getContactList());
        comp.setUpdateTime(new Date());

        companyRepo.save(comp);
    }

    public void delete(Long companyId) {
        Company company = companyRepo.findOne(companyId);
        company.setDelFlag(true);
        companyRepo.save(company);
    }

    public void saveContact(Contact contact) {
        checkExistedContact(contact);
        contact.setCreateTime(new Date());
        contact.setUpdateTime(new Date());
        contactRepo.save(contact);
    }

    public void updateContact(Contact contact) {
        Long contactId = contact.getId();
        Contact cont = contactRepo.findOne(contactId);
        if (cont == null) {
            throw new NotFoundException("");
        }

        checkExistedContact(contact);

        cont.setName(contact.getName());
        cont.setEmail(contact.getEmail());
        cont.setTelephone(contact.getTelephone());
        cont.setUpdateTime(new Date());
        contactRepo.save(contact);
    }

    public void deleteContact(Long contactId) {
        Contact contact = contactRepo.findOne(contactId);
        if (contact == null) {
            throw new NotFoundException("Not Found");
        }
        companyRepo.delete(contactId);
    }

    private void checkExistedContact(Contact contact) {
        String name = contact.getName();
        String telephone = contact.getTelephone();
        Long compId = contact.getCompanyId();
        Long contactId = contact.getId();

        if (compId == null) {
            throw new BadRequestParamException("Bad params");
        }
        List<Contact> contactList = contactRepo.findByCompanyId(compId);
        boolean existedContact = contactList.stream()
                .anyMatch(con ->
                        con.getName().equals(name)
                        && con.getTelephone().equals(telephone)
                        && !con.getId().equals(contactId)
                );
        if (existedContact) {
            throw new BadRequestParamException("Has Existed");
        }
    }

    private void checkExistedComp(Company company) {
        String email = company.getEmail();
        String name = company.getName();
        UserAuth auth = authRepo.findFirstByUsername(email);
        if (auth == null) {
            throw new DataExistedException("Auth Error");
        }
        List<Company> existedCompList = companyRepo.findByNameAndDelFlagFalse(name);
        boolean hasExistedComp = existedCompList.stream()
                .anyMatch(
                        comp -> comp.getName().equals(name)
                        && !company.getId().equals(comp.getId())
                );

        if (hasExistedComp) {
            throw new DataExistedException("Has Existed");
        }
    }
}
