package com.wzq.encrypt.util;

public class ByteUtil {

    // byte数组转hex字符串
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String sTemp;

        for (byte aByte : bytes) {
            sTemp = Integer.toHexString(0xFF & aByte);
            if (sTemp.length() < 2) {
                builder.append(0);
            }
            builder.append(sTemp.toUpperCase());
        }

        return builder.toString();
    }

    // byte数组转hex字符串
    public static String bytesToHexString(byte[] bytes, int length) {
        StringBuilder builder = new StringBuilder();
        String sTemp;

        for (int i = 0; i < length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2) {
                builder.append(0);
            }
            builder.append(sTemp.toUpperCase());
        }

        return builder.toString();
    }

    // byte转hex字符串
    public static String bytesToHexString(byte bytes) {
        StringBuilder builder = new StringBuilder();
        String sTemp;

        sTemp = Integer.toHexString(0xFF & bytes);
        if (sTemp.length() < 2) {
            builder.append(0);
        }
        builder.append(sTemp.toUpperCase());

        return builder.toString();
    }

    // hex字符串转byte
    public static byte[] hexStringToByte2(String hex) {
        byte[] data = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }

        return data;
    }

}
