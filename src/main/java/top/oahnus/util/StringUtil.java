package top.oahnus.util;

/**
 * Created by oahnus on 2017/2/25.
 */
public class StringUtil {
    private StringUtil() {}

    public static boolean isEmpty(String str) {
        if(str == null || str.isEmpty()){
            return true;
        }
        return false;
    }
}
