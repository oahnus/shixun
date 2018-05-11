package top.oahnus.enums;

public enum ErrorType {
    // 数据库读写失败（测试环境）
    DATABASE_ERROR("ERROR.DATABASE"),
    // 未知错误
    UNKNOWN_ERROR("ERROR.UNKNOWN"),
    // 缺少权限
    NO_AUTH_ERROR("ERROR.NO_AUTH"),
    // 资源已存在
    EXISTED_ERROR("ERROR.EXISTED"),
    // 资源不存在
    NOT_FOUND_ERROR("ERROR.NOT_FOUND"),
    // 请求错误
    INVALID_REQUEST_ERROR("ERROR.INVALID_REQUEST"),
    // 参数非法
    INVALID_PARAM_ERROR("ERROR.INVALID_PARAM"),
    // 文件上传错误
    FILE_UPLOAD_ERROR("ERROR.FILE_UPLOAD"),
    // 不存在的API
    INVALID_API_ERROR("ERROR.INVALID_API"),
    // 请求格式不合法（非JSON）
    UNSUPPORTED_MEDIA("ERROR.UNSUPPORTED_MEDIA"),
    // 请求方法错误（GET/POST/PATCH/PUT/DELETE）
    INVALID_METHOD_ERROR("ERROR.INVALID_REQUEST_METHOD");
    private final String code;

    ErrorType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}