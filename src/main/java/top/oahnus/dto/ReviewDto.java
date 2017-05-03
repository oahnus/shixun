package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "评论人name不能为空")
    private String fromUserName;
    @NotEmpty(message = "被评论人username不能为空")
    private String toUserUsername;
    @NotEmpty(message = "被评论人name不能为空")
    private String toUsername;
    @Pattern(regexp = "^STUDENT|TEACHER|COMPANY$", message = "用户角色错误")
    private String toUserType;
    @NotEmpty(message = "评论课程id不能为空")
    private String courseId;
    @NotEmpty(message = "评论课程名不能为空")
    private String courseName;
    @NotNull(message = "评论等级不能为空")
    private Integer rate;
    @NotEmpty(message = "评论内容不能为空")
    private String content;
}
