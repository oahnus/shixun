package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.TeacherDto;

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
    private String email;

    public Teacher() {}

    public Teacher(String workerId, String name, String profession, String depart, String sex, String jobTitle, String email) {
        this.workerId = workerId;
        this.name = name;
        this.profession = profession;
        this.depart = depart;
        this.sex = sex;
        this.jobTitle = jobTitle;
        this.email = email;
    }

    public Teacher(TeacherDto teacherDto) {
        this.workerId = teacherDto.getWorkerId();
        this.name = teacherDto.getName();
        this.profession = teacherDto.getProfession();
        this.depart = teacherDto.getDepart();
        this.sex = teacherDto.getSex();
        this.jobTitle = teacherDto.getJobTitle();
        this.email = teacherDto.getEmail();
    }
}
