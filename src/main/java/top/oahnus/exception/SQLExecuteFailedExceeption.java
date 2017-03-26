package top.oahnus.exception;

/**
 * Created by oahnus on 2017/3/23 21:05.
 * SQL语句执行错误
 */
public class SQLExecuteFailedExceeption extends ClientException {
    public SQLExecuteFailedExceeption() {
    }

    public SQLExecuteFailedExceeption(String message) {
        super(message);
    }
}
