package top.oahnus.common.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import top.oahnus.domain.Department;
import top.oahnus.domain.Profession;
import top.oahnus.domain.Teacher;
import top.oahnus.enums.SexEnum;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by oahnus on 2018/5/27
 * 13:30.
 */
@Data
public class TeacherPayload {
    private Long id;
    @NotEmpty(message = "workerId should not empty")
    private String workerId;
    @NotEmpty(message = "name should not empty")
    private String name;
    @NotNull(message = "professionId should not null")
    private Long professionId;
    @NotNull(message = "departId should not null")
    private Long departId;
    private Integer sex;
    private String jobTitle;
    @Email(message = "email formatter error")
    private String email;

    public Teacher toTeacher() {
        Teacher teacher = new Teacher();
        Profession profession = new Profession();
        profession.setId(professionId);
        Department department = new Department();
        department.setId(departId);

        teacher.setId(id);
        teacher.setName(name);
        teacher.setWorkerId(workerId);
        teacher.setSex(SexEnum.getSex(sex));
        teacher.setJobTitle(jobTitle);
        teacher.setEmail(email);
        teacher.setProfession(profession);
        teacher.setDepartment(department);
        teacher.setUpdateTime(new Date());
        return teacher;
    }
}
