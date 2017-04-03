package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.CompanyDto;


/**
 * Created by oahnus on 2017/2/27.
 * 20:17
 */
@Data
public class Company {
    private String id;
    private String name;
    private String contact;
    private String contactPhone;
    private String address;
    private String email;

    public Company(){}

    public Company(String name, String contact, String contactPhone, String address, String email) {
        this.name = name;
        this.contact = contact;
        this.contactPhone = contactPhone;
        this.address = address;
        this.email = email;
    }

    public Company(CompanyDto companyDto) {
        this.id = companyDto.getId();
        this.name = companyDto.getName();
        this.contact = companyDto.getContact();
        this.contactPhone = companyDto.getContactPhone();
        this.address = companyDto.getAddress();
        this.email = companyDto.getEmail();
    }
}
