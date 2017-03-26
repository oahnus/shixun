package top.oahnus.exception;

/**
 * Created by oahnus on 2017/2/25.
 * 客户端错误
 */
public class ClientException extends RuntimeException {
    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }
}
