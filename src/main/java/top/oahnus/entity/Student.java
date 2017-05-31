package top.oahnus.entity;

import lombok.Data;
import top.oahnus.payload.StudentPayload;

import javax.persistence.*;

/**
 * Created by oahnus on 2017/3/2.
 * 21:30
 */
@Data
@Entity(name = "student")
public class Student extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 学号
    private String studentNum;
    // 姓名
    private String name;
    // 性别
    private String sex;
    // 专业
    private Long professionId;
    @Transient
    private String professionName;
    private Long departId;
    @Transient
    private String departName;
    private String email;

    public Student() {}

    public static Student fromPayload(StudentPayload payload) {
        Student student = new Student();
        student.setId(payload.getId());
        student.setStudentNum(payload.getStudentNum());
        student.setName(payload.getName());
        student.setSex(payload.getSex());
        student.setEmail(payload.getEmail());
        student.setProfessionId(payload.getProfessionId());
        student.setDepartId(payload.getDepartId());
        return student;
    }
}
