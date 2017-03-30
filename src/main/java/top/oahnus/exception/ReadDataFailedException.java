package top.oahnus.exception;

/**
 * Created by oahnus on 2017/3/26 16:17.
 * 读取数据失败
 */
public class ReadDataFailedException extends ClientException {
    public ReadDataFailedException() {
    }

    public ReadDataFailedException(String message) {
        super(message);
    }
}
