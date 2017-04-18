package top.oahnus.controller;

/**
 * Created by oahnus on 2017/2/26.
 * 17:18
 */
public enum ServerState {
    SUCCESS(0),
    FAILED(-1),
    NO_AUTH_ERROR(10000),
    LOGIN_FAILED_ERROR(10001),
    REQUEST_PARAMETER_ERROR(20000),
    FILE_UPLOAD_ERROR(20001),
    DATA_NOT_FOUND_ERROR(30000),
    DATA_EXISTED_ERROR(30001),
    READ_DATA_FAILED(30002),
    SQL_EXECUTE_FAILED(40000),
    INNER_SERVER_ERROR(50000);

    private int code;

    ServerState(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
