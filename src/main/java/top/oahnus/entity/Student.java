package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.StudentDto;

/**
 * Created by oahnus on 2017/3/2.
 * 21:30
 */
@Data
public class Student {
    private Long id;

    private String studentNum;

    private String name;

    private String profession;

    private String depart;

    public Student() {}

    public Student(String studentNum, String name, String profession, String depart) {
        this.studentNum = studentNum;
        this.name = name;
        this.profession = profession;
        this.depart = depart;
    }

    public Student(StudentDto studentDto) {
        this.id = studentDto.getId() == null ? null : studentDto.getId();
        this.studentNum = studentDto.getStudentNum();
        this.name = studentDto.getName();
        this.depart = studentDto.getDepart();
        this.profession = studentDto.getProfession();
    }
}
