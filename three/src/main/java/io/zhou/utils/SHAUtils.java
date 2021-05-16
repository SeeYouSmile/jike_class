package io.zhou.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtils {

    public static String sha256(byte[] bytes){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes);
            return byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String byte2Hex(byte[] bytes){
        StringBuffer sb=new StringBuffer();
        String temp;
        for (byte aByte : bytes) {
            temp=Integer.toHexString(aByte&0xff);
            if(temp.length()==1){
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
