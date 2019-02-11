package com.wzq.encrypt.aes;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 128 CBC PKCS5Padding
 */
public class AesUtil {
    private static final String AES_NAME = "AES";
    private static final String AES_MODEL = "AES/CBC/PKCS5Padding";

    private static byte[] key = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * byte加密
     */
    public static byte[] encryptByte(byte[] content) {
        byte[] result = null;

        try {
            result = getCipher(Cipher.ENCRYPT_MODE).doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * byte解密
     */
    public static byte[] decryptByte(byte[] content) {
        byte[] result = null;

        try {
            result = getCipher(Cipher.DECRYPT_MODE).doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * String加密，返回base64后的字符串
     */
    public static String encryptString(String content) {
        try {
            byte encryptByte[] = encryptByte(content.getBytes("utf-8"));

            return Base64.encodeToString(encryptByte, Base64.URL_SAFE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * String解密，参数必须是base64过的
     */
    public static String decryptString(String content) {
        try {
            byte decryptByte[] = decryptByte(Base64.decode(content, Base64.URL_SAFE));

            return new String(decryptByte, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Cipher getCipher(int mode) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_NAME);
        Cipher cipher = Cipher.getInstance(AES_MODEL);
        cipher.init(mode, secretKeySpec, new IvParameterSpec(iv));

        return cipher;
    }

}
