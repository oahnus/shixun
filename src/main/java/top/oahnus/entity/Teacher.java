package top.oahnus.entity;

import lombok.Data;

/**
 * Created by oahnus on 2017/3/1.
 * 23:23
 */
@Data
public class Teacher {
    private Long id;
    private String workerId;
    private String name;
    private String profession;
    private String depart;

    public Teacher() {}

    public Teacher(String workerId, String name, String profession, String depart) {
        this.workerId = workerId;
        this.name = name;
        this.profession = profession;
        this.depart = depart;
    }
}
