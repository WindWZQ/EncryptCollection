package com.wzq.encrypt.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 1.DES加密，支持ECB和CBC两种工作模式
 * <p>
 * ECB：电子密本方式
 * 优点：简单，有利于并行计算
 * 缺点：不能隐藏明文的模式，容易被攻击，不安全
 * <p>
 * <p>
 * CBC：密码分组链接
 * 优点：安全性好于ECB，是SSL、IPSec的标准
 * 缺点：不利于并行计算，误差传递
 * <p>
 * <p>
 * 2.DESEDE加密，三倍长的Des加密
 */
public class DesUtil {
    private static final String DES_BASE = "DES";

    private static final String DES_ECB = "DES/ECB/PKCS5Padding";
    private static final String DES_CBC = "DES/CBC/PKCS5Padding";

    private static final String DESEDE_BASE = "DESede";

    //
    public static final byte DEFAULT_KEY[] = {1, 2, 3, 4, 5, 6, 7,8};
    public static final byte DEFAULT_IV[] = {8, 7, 6, 5, 1, 2, 3};

    // ede秘钥必须是16或24字节
    public static final byte DEFAULT_KEY_EDE[] = {1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * ECB加解密
     *
     * @param data 源数据
     * @param key  秘钥
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] ecb(byte[] data, byte[] key, int mode) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_BASE);
            SecretKey secureKey = keyFactory.generateSecret(new DESKeySpec(key));
            Cipher cipher = Cipher.getInstance(DES_ECB);
            cipher.init(mode, secureKey, new SecureRandom());

            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * CBC加解密
     *
     * @param data 源数据
     * @param key  秘钥
     * @param iv   向量
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] cbc(byte[] data, byte[] key, byte[] iv, int mode) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_BASE);
            SecretKey secretKey = keyFactory.generateSecret(new DESKeySpec(key));
            Cipher cipher = Cipher.getInstance(DES_CBC);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(mode, secretKey, ivSpec);

            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // desede加密方法
    public static byte[] ede(byte[] data, byte[] key, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, DESEDE_BASE);
            Cipher c1 = Cipher.getInstance(DESEDE_BASE);
            c1.init(mode, secretKey);

            return c1.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
