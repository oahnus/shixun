package top.oahnus.exception;

/**
 * Created by oahnus on 2017/3/23 21:05.
 */
public class SQLExecuteExceeption extends ClientException {
    public SQLExecuteExceeption() {
    }

    public SQLExecuteExceeption(String message) {
        super(message);
    }
}
