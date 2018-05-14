package top.oahnus.common.payload.pageForm;

import lombok.Data;

/**
 * Created by oahnus on 2018/5/13
 * 20:46.
 */
@Data
public class StudentPageForm {
    private PageForm page;
    private String name;
    private String stuNumber;
    private Long departId;
    private Long professionId;
}
