package top.oahnus.enums;

/**
 * Created by oahnus on 2018/5/10
 * 9:56.
 */
public enum SexEnum {
    UNKNOWN(0),
    MALE(1),
    FEMALE(2);

    private int code;
    SexEnum(int code) {
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public static SexEnum getSex(int code) {
        for (SexEnum sex: values()) {
            if (sex.getCode() == code) {
                return sex;
            }
        }
        return null;
    }

    public static SexEnum convert(String name) {
        switch (name) {
            case "男":
                return SexEnum.MALE;
            case "女":
                return SexEnum.FEMALE;
            default:
                return SexEnum.UNKNOWN;
        }
    }
}
