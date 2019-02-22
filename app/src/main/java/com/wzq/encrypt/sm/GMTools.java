package com.wzq.encrypt.sm;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;

public class GMTools {
    // https://blog.csdn.net/Soul_Programmer_Swh/article/details/80375958

    public static void main(String[] args) {
        //SM2Verify("1234567812345678", "112233445566778899", "6329F03955A907D155E3E5740FA15FE1454B40B7E99325B532C1F3F1B917E9BC03B9BCFEDC8B5D84351D0ECE43301552C5DD45FFDB18CA4F490F62D08CB5C3DD",  "0A6E2C5FF0901BBB42688A516FCEEA60099F07384B56DB51B42E369E23420771", "C1B04A6E8BD39C86901921019A2A46B4B2F6E13D708F957C9DE99572E9505A0C");
        System.out.println(SM3("616263"));
        System.out.println(SM4DecryptDataECB("11223311223311223311223311223311", "404142434445464748494A4B4C4D4E4F"));

    }

    public static boolean SM2Verify(String userId, String plainText, String pubk, String rp, String sp) {
        // 国密规范测试用户ID
//		String userId = "1234567812345678";
        System.out.println("ID: " + Util.getHexString(userId.getBytes()));
        System.out.println("");
        byte[] sourceData = Util.hexStringToBytes(plainText);
        String pubkS = new String(Base64.encode(Util.hexToByte(pubk)));
        BigInteger r = new BigInteger(rp, 16);
        BigInteger s = new BigInteger(sp, 16);
        System.out.println("r: " + r);
        System.out.println("s: " + s);
        System.out.println("");
        DERInteger d_r = new DERInteger(r);
        DERInteger d_s = new DERInteger(s);
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(d_r);
        v2.add(d_s);
        DERObject sign = new DERSequence(v2);
        byte[] c = sign.getDEREncoded();
        System.out.println("验签: ");
        boolean vs = SM2Utils.verifySign(userId.getBytes(), Base64.decode(pubkS.getBytes()), sourceData, c);
        System.out.println("验签结果: " + vs);
        System.out.println("");
        return vs;
    }

    public static String SM3(String plainText) {
        byte[] md = new byte[32];
        byte[] msg1 = Util.hexToByte(plainText);
        //byte[] msg1 = "abc".getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        System.out.println(s);
        return s;
    }

    public static String SM4EncryptDataECB(String plainText, String key) {
        SM4Utils sm4 = new SM4Utils();
        sm4.setHexString(true);
        sm4.setSecretKey(key);
        System.out.println("ECB模式");
        String cipherText = sm4.encryptData_ECB(plainText);
        System.out.println("密文: " + cipherText);
        System.out.println("");
        return cipherText.substring(0, 32);
    }

    public static String SM4DecryptDataECB(String cipherText, String key) {
        SM4Utils sm4 = new SM4Utils();
        sm4.setHexString(true);
        sm4.setSecretKey(key);
        String plainText = sm4.decryptData_ECB(cipherText);
        System.out.println("明文: " + plainText);
        System.out.println("");
        return plainText;
    }


}
