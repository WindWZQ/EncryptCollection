package com.wzq.encrypt.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 1.DES加密，支持ECB和CBC两种工作模式
 *   ECB：电子密本方式
 *   优点：分块处理，并行处理
 *   缺点：同样的原文得到相同的密文，容易被攻击
 *
 *   CBC：密码分组链接
 *   优点：同样的原文得到不同的密文，原文的改变影响后面全部密文
 *   缺点：加密需要串行处理，误差传递
 *
 * 2.DESEDE加密，三倍长的Des加密
 */
public class DesUtil {
    private static final String DES_BASE = "DES";

    public static final String DES_ECB = "DES/ECB/PKCS5Padding";
    public static final String DES_CBC = "DES/CBC/PKCS5Padding";

    // desede加密
    private static final String DESEDE_BASE = "DESede";

    public static final byte DEFAULT_KEY[] = {1, 2, 3, 4, 5, 6, 7, 8};
    public static final byte DEFAULT_IV[] = {1, 2, 3, 4, 5, 6, 7, 8};

    // des加密
    public static byte[] encrypt(byte[] data, byte[] key, String mode) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key);
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_BASE);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(mode);
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // des解密
    public static byte[] decrypt(byte[] src, byte[] key, String mode) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_BASE);
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(mode);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // desede加密方法
    public static byte[] encryptEde(byte[] src, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, DESEDE_BASE);
            Cipher c1 = Cipher.getInstance(DESEDE_BASE);
            c1.init(Cipher.ENCRYPT_MODE, secretKey);
            return c1.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // desede解密函数
    public static byte[] decryptEde(byte[] src, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, DESEDE_BASE);
            Cipher c1 = Cipher.getInstance(DESEDE_BASE);
            c1.init(Cipher.DECRYPT_MODE, secretKey);
            return c1.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
