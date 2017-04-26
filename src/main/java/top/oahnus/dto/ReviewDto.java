package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by oahnus on 2017/4/25
 * 15:21.
 */
@Data
public class ReviewDto {
    @NotEmpty(message = "评论人username不能为空")
    private String fromUserUsername;
    @Pattern(regexp = "^STUDENT|TEACHER|COMPANY$", message = "用户角色错误")
    private String fromUserType;
    @NotEmpty
    private String fromUserName;
    @NotEmpty
    private String toUserUsername;
    @NotEmpty
    private String toUsername;
    @Pattern(regexp = "^STUDENT|TEACHER|COMPANY$", message = "用户角色错误")
    private String toUserType;
    @NotEmpty
    private String courseId;
    @NotEmpty
    private String courseName;
    @NotEmpty(message = "评论内容不能为空")
    private String content;
}
