package top.oahnus.common.payload.pageForm;

import lombok.Data;

/**
 * Created by oahnus on 2018/5/13
 * 20:39.
 */
@Data
public class TeacherPageForm {
    private PageForm page;
    private String name;
    private String workerId;
    private Long departId;
    private Long professionId;
}
