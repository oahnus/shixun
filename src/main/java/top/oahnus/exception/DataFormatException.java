package top.oahnus.exception;

/**
 * Created by oahnus on 2017/2/27.
 * 数据格式错误
 */
public class DataFormatException extends ClientException {
    public DataFormatException() {
    }

    public DataFormatException(String message) {
        super(message);
    }
}
