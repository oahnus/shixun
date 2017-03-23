package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by oahnus on 2017/3/23 20:56.
 * 公司
 */
@Data
public class CompanyDto {
    private String id;
    @NotEmpty(message = "公司名称不能为空")
    private String name;
    @NotEmpty(message = "公司联系人不能为空")
    private String contact;
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$")
    private String contactPhone;
    private String address;
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$")
    private String email;
}
