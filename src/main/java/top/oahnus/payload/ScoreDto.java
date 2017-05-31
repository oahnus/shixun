package top.oahnus.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import top.oahnus.enums.AuthType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by oahnus on 2017/4/19
 * 13:29.
 */
@Data
public class ScoreDto {
    private Long id;
    @NotEmpty(message = "选课Id不能为空")
    private Long courseSelectionId;
    @NotEmpty(message = "学生ID不能为空")
    private Long studentId;
    @Min(message = "分数要大于0", value = 0)
    @Max(message = "分数要小于100", value = 100)
    @NotNull(message = "分数不能为空")
    private Float score;
    @NotNull(message = "角色类型不能为空")
    private AuthType authType;
}
