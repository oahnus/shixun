package top.oahnus.common.dto;

import lombok.Data;
import top.oahnus.enums.ErrorType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oahnus on 2018/5/9
 * 20:59.
 */
@Data
public class ResultData {
    private String code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    public ResultData() {
        code = "SUCCESS";
        msg = "success";
    }

    public ResultData(ErrorType type, String msg) {
        this.code = type.getCode();
        this.msg = msg;
    }

    public ResultData data(String key, Object val) {
        data.put(key, val);
        return this;
    }
}
