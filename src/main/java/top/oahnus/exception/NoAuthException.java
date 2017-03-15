package top.oahnus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by oahnus on 2017/2/25.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoAuthException extends ClientException {
    public NoAuthException(String message) {
        super(message);
    }
}
