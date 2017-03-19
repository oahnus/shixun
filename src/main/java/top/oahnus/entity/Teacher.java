package top.oahnus.entity;

import lombok.Data;

/**
 * Created by oahnus on 2017/3/1.
 * 23:23
 */
@Data
public class Teacher {
    private String id;
    private String workerId;
    private String name;
    private String profession;
    private String depart;
    // 性别
    private String sex;
    // 职称
    private String jobTitle;

    public Teacher() {}

    public Teacher(String workerId, String name, String profession, String depart,String sex, String jobTitle) {
        this.workerId = workerId;
        this.name = name;
        this.profession = profession;
        this.depart = depart;
        this.sex = sex;
        this.jobTitle = jobTitle;
    }
}
