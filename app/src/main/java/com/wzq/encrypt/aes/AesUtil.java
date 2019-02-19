package com.wzq.encrypt.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
    private static final String AES_BASE = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";

    // 16字节秘钥，也就是128位
    public static final byte DEFAULT_KEY[] = {1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4, 3, 2, 1};
    public static final byte DEFAULT_IV[] = {1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4, 3, 2, 1};

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
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_BASE);
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(mode, secretKeySpec, new IvParameterSpec(iv));

            result = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
