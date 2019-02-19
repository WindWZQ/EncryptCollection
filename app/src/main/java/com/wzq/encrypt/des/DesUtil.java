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
 * 缺点：不能隐藏明文的模式，容易被攻击，不安全，不建议使用
 * <p>
 * <p>
 * CBC：密码分组链接
 * 优点：安全性好于ECB，是SSL、IPSec的标准
 * 缺点：不利于并行计算，误差传递
 * <p>
 * <p>
 * 2.DESEDE加密，DESede是由DES对称加密算法改进后的一种对称加密算法。使用 168 位的密钥对资料进行三次加密的一种机制；
 * 它通常（但非始终）提供极其强大的安全性。
 */
public class DesUtil {
    private static final String DES_BASE = "DES";

    private static final String DES_ECB = "DES/ECB/PKCS5Padding";
    private static final String DES_CBC = "DES/CBC/PKCS5Padding";

    private static final String DESEDE_BASE = "DESede";

    // 秘钥至少是8字节
    public static final byte DEFAULT_KEY[] = {1, 2, 3, 4, 5, 6, 7, 8};

    // cbc向量必须是8字节
    public static final byte DEFAULT_IV[] = {8, 7, 6, 5, 1, 2, 3, 4};

    // ede秘钥必须是16或24字节
    public static final byte DEFAULT_KEY_EDE[] = {1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * ECB
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
     * CBC
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

    /**
     * Desede
     *
     * @param data 源数据
     * @param key  秘钥
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
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
