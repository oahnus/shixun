package top.oahnus.entity;

import lombok.Data;


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

    public Company(){}

    public Company(String name, String contact, String address, String contactPhone) {
        this.name = name;
        this.contact = contact;
        this.contactPhone = contactPhone;
        this.address = address;
    }
}
