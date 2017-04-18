package top.oahnus.exception;

/**
 * Created by oahnus on 2017/4/18
 * 17:19.
 */
public class LoginFailedException extends ClientException {
    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
