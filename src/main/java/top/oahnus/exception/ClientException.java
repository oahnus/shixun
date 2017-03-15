package top.oahnus.exception;

/**
 * Created by oahnus on 2017/2/25.
 */
public class ClientException extends RuntimeException {
    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }
}
