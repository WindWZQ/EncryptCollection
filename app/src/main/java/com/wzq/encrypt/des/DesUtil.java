package com.wzq.encrypt.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DesUtil {

    // des加密
    private static final String Algorithm_Des = "DES";

    // desede加密
    private static final String Algorithm_Desede = "DESede";

    public static final byte DEFAULT_KEY[] = {0, 0, 0, 0, 0, 0, 0, 0};
    public static final byte DEFAULT_VALUE[] = {0, 0, 0, 0, 0, 0, 0, 0};

    // des加密
    public static byte[] encrypt(byte[] data, byte[] password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password);
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm_Des);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(Algorithm_Des);
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }


    // des解密
    public static byte[] decrypt(byte[] src, byte[] password) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(password);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm_Des);
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(Algorithm_Des);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // desede加密方法
    public static byte[] encryptEde(byte[] src, byte[] password) {
        try {
            SecretKey key = new SecretKeySpec(password, Algorithm_Desede);
            Cipher c1 = Cipher.getInstance(Algorithm_Desede);
            c1.init(Cipher.ENCRYPT_MODE, key);
            return c1.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // desede解密函数
    public static byte[] decryptEde(byte[] src, byte[] password) {
        try {
            SecretKey key = new SecretKeySpec(password, Algorithm_Desede);
            Cipher c1 = Cipher.getInstance(Algorithm_Desede);
            c1.init(Cipher.DECRYPT_MODE, key);
            return c1.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
