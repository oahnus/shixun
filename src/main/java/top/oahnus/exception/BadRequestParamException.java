package top.oahnus.exception;

/**
 * Created by oahnus on 2017/3/7.
 * 21:40
 */
public class BadRequestParamException extends ClientException {
    public BadRequestParamException(String message) {
        super(message);
    }

    public BadRequestParamException() {
    }
}
