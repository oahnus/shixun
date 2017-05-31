package top.oahnus.entity;

import lombok.Data;
import top.oahnus.payload.TeacherPayload;

import javax.persistence.*;

/**
 * Created by oahnus on 2017/3/1.
 * 23:23
 */
@Data
@Entity(name = "teacher")
public class Teacher extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workerId;
    private String name;
    private Long professionId;
    private Long departId;
    // 性别
    private String sex;
    // 职称
    private String jobTitle;
    private String email;

    public Teacher() {}

    public static Teacher fromPayload(TeacherPayload payload) {
        Teacher teacher = new Teacher();
        teacher.setId(payload.getId());
        teacher.setWorkerId(payload.getWorkerId());
        teacher.setName(payload.getName());
        teacher.setSex(payload.getSex());
        teacher.setJobTitle(payload.getJobTitle());
        teacher.setEmail(payload.getEmail());
        teacher.setProfessionId(payload.getProfessionId());
        teacher.setDepartId(payload.getDepartId());

        return teacher;
    }
}
