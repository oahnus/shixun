package top.oahnus.enums;

/**
 * Created by oahnus on 2017/2/22.
 * 20:14
 * 用户角色
 */
public enum AuthType {
    // 管理员
    ADMIN,
    // 公司
    COMPANY,
    // 教师
    TEACHER,
    // 学生
    STUDENT;

    public static AuthType getType(int code) {
        for (AuthType type: AuthType.values()) {
            if(type.ordinal() == code) {
                return type;
            }
        }
        return null;
    }
}
