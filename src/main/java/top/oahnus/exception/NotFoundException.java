package top.oahnus.exception;

/**
 * Created by oahnus on 2017/2/26.
 * 数据未找到
 */
public class NotFoundException extends ClientException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
