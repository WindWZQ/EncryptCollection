package com.wzq.encrypt.rsa;

import android.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RsaUtil {
    private static final String RSA_BASE = "RSA";
    private static final String RSA_MODULE = "RSA/NONE/PKCS1Padding";

    /**
     * 产生密钥对
     *
     * @param keyLength 密钥长度，小于1024长度的密钥已经被证实是不安全的，通常设置为1024或者2048，建议2048
     * @return 密钥对
     */
    public static KeyPair generateKeyPair(int keyLength) {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_BASE);
            keyPairGenerator.initialize(keyLength);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return keyPair;
    }

    /**
     * 单次加密的源数据最大长度不能超过key的长度
     * 比如key的长度是2048位，也就是256字节，src的长度也不能超过256字节
     * 参考下面的分段加密方法
     *
     * @param src  源数据
     * @param key  公钥或者私钥
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] process(byte[] src, Key key, int mode) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_MODULE);
            cipher.init(mode, key);
            result = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * todo 根据keyLength分段处理
     */
    public static byte[] processSection(byte[] src, Key key, int keyLength, int mode) {
        int singleLength = keyLength / 8;

        try {
            if (src.length <= singleLength) {
                return process(src, key, mode);
            }

            int count = src.length / singleLength;
            for (int i = 0; i < count; i++) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将字符串形式的公钥转换为公钥对象
     *
     * @param publicKeyStr 公钥字符串
     * @return 公钥
     */
    public static PublicKey key2PublicKey(String publicKeyStr) {
        PublicKey publicKey = null;
        try {
            byte[] keyBytes = Base64.decode(publicKeyStr, Base64.DEFAULT);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_BASE);
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publicKey;
    }

    /**
     * 将字符串形式的私钥，转换为私钥对象
     *
     * @param privateKeyStr 私钥字符串
     * @return 私钥
     */
    public static PrivateKey key2PrivateKey(String privateKeyStr) {
        PrivateKey privateKey = null;
        try {
            byte[] keyBytes = Base64.decode(privateKeyStr, Base64.DEFAULT);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_BASE);
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return privateKey;
    }
}
