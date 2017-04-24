package top.oahnus.dto;

import lombok.Data;
import top.oahnus.enums.ServerState;

/**
 * Created by oahnus on 2017/2/26.
 */
@Data
public class ResponseData<T> {
    private int code;

    private T data;

    private String msg;

    public ResponseData(ServerState state, String msg) {
        this.msg = msg;
        this.data = null;
        this.code = state.getCode();
    }

    public ResponseData(ServerState state, T data, String msg) {
        this.code = state.getCode();
        this.data = data;
        this.msg = msg;
    }
}
