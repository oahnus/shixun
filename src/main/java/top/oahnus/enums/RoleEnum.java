package top.oahnus.enums;

/**
 * Created by oahnus on 2018/5/9
 * 15:31.
 */
public enum RoleEnum {
    ANONYMOUS(0),
    ADMIN(1),
    COMPANY(2),
    TEACHER(3),
    STUDENT(4);

    private int code;
    RoleEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static RoleEnum getRole(int code) {
        for (RoleEnum role: values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        return null;
    }
}
