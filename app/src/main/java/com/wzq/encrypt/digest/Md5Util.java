package com.wzq.encrypt.digest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Md5Util {

    /**
     * @param src 源数据
     * @return md5结果
     */
    public static String encode(byte[] src) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(src);
            byte s[] = m.digest();

            StringBuilder result = new StringBuilder();
            int temp;
            for (byte value : s) {
                temp = value & 0xFF;
                if (temp < 16) {
                    result.append(0);
                }
                result.append(Integer.toHexString(temp));
            }

            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String encode(String src) {
        return encode(src.getBytes(StandardCharsets.UTF_8));
    }

}
