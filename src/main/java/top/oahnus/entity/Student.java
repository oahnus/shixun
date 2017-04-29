package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.StudentDto;

/**
 * Created by oahnus on 2017/3/2.
 * 21:30
 */
@Data
public class Student extends User{
    private String id;
    // 学号
    private String studentNum;
    // 姓名
    private String name;
    // 性别
    private String sex;
    // 专业
    private String profession;
    // 学院
    private String depart;
    private String email;

    public Student() {}

    public Student(String studentNum, String name, String profession, String depart, String sex, String email) {
        this.studentNum = studentNum;
        this.name = name;
        this.profession = profession;
        this.depart = depart;
        this.sex = sex;
        this.email = email;
    }

    public Student(StudentDto studentDto) {
        this.id = studentDto.getId() == null ? "" : studentDto.getId();
        this.studentNum = studentDto.getStudentNum();
        this.name = studentDto.getName();
        this.depart = studentDto.getDepart();
        this.profession = studentDto.getProfession();
        this.sex = studentDto.getSex();
        this.email = studentDto.getEmail();
    }
}
