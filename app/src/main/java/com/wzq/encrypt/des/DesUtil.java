package com.wzq.encrypt.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtil {
    private static final String DES_BASE = "DES";
    private static final String DES_EDE_BASE = "DESede";

    private static final String DES_CBC = "DES/CBC/PKCS7Padding";
    private static final String DES_EDE = "DESede/CBC/PKCS7Padding";
    private static final String DES_EDE_ECB = "DESede/ECB/NoPadding";

    // 秘钥必须是8字节
    public static final byte DEFAULT_KEY[] = {1, 2, 3, 4, 5, 6, 7, 8};

    // 向量必须是8字节
    public static final byte DEFAULT_IV[] = {8, 7, 6, 5, 4, 3, 2, 1};

    // ede秘钥必须>=24字节
    public static final byte DEFAULT_KEY_EDE[] = {1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * CBC
     *
     * @param src  源数据
     * @param key  秘钥
     * @param iv   向量
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] process(byte[] src, byte[] key, byte[] iv, int mode) {
        byte[] result = null;
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_BASE);
            SecretKey secretKey = keyFactory.generateSecret(new DESKeySpec(key));
            Cipher cipher = Cipher.getInstance(DES_CBC);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(mode, secretKey, ivSpec);

            result = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Desede
     *
     * @param src  源数据
     * @param key  秘钥
     * @param iv   向量
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] processEde(byte[] src, byte[] key, byte[] iv, int mode) {
        byte[] result = null;
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_EDE_BASE);
            SecretKey secretKey = keyFactory.generateSecret(new DESedeKeySpec(key));
            Cipher cipher = Cipher.getInstance(DES_EDE);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(mode, secretKey, ivSpec);

            result = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] processEdeEcb(byte[] src, byte[] key, int mode) {
        byte[] result = null;
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_EDE_BASE);
            SecretKey secretKey = keyFactory.generateSecret(new DESedeKeySpec(key));
            Cipher cipher = Cipher.getInstance(DES_EDE_ECB);
            cipher.init(mode, secretKey);

            result = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
