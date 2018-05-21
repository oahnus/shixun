package top.oahnus.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by oahnus on 2017/2/25.
 */
public class MD5Util {
    private MD5Util() {}

    public static String getMD5(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] bytes = digest.digest(str.getBytes());
            StringBuffer buffer = new StringBuffer();
            for(byte b: bytes){
                if ((b & 0xFF) < 16) buffer.append(0);
                buffer.append(Integer.toHexString(b&0xFF));
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMD5Upper(String str) {
        return getMD5(str).toUpperCase();
    }

    public static void main(String[] args){
        System.out.println(getMD5("13141101"));
    }
}
