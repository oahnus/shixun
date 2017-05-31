package top.oahnus.entity;

import lombok.Data;
import top.oahnus.payload.CompanyPayload;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created by oahnus on 2017/2/27.
 * 20:17
 */
@Data
@Entity(name = "company")
public class Company extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contact;
    private String contactPhone;
    private String address;
    private String email;

    public Company(){}

    public static Company fromPayload(CompanyPayload payload) {
        Company company = new Company();
        company.setId(payload.getId());
        company.setName(payload.getName());
        company.setContact(payload.getContact());
        company.setContactPhone(payload.getContactPhone());
        company.setAddress(payload.getAddress());
        company.setEmail(payload.getEmail());
        return company;
    }
}
