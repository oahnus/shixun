package top.oahnus.exception;

/**
 * Created by oahnus on 2017/3/11.
 * 21:07
 */
public class DataExistedException extends ClientException {
    public DataExistedException() {
    }

    public DataExistedException(String message) {
        super(message);
    }
}
