# EncryptCollection
Java常用加密算法整理，包括des aes rsa md5 sha1 sm2 sm3 sm4。其他未包括算法欢迎PR。
## Des
### CBC
``` java
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
    public static byte[] process(byte[] src, byte[] key, byte[] iv, int mode)
```
1.密钥必须是8字节  
2.向量必须是8字节  
3.根据传入的mode决定加密或解密模式，下列各算法同  
4.如果想传入传出的是字符串，可以用base64自行再封装一下，下列各算法同 
### EDE
``` java
    /**
     * Desede
     *
     * @param src  源数据
     * @param key  秘钥
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] processEde(byte[] src, byte[] key, byte[] iv, int mode)
```
1.密钥必须是16或24字节
## Aes
### CBC
``` java
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
    public static byte[] process(byte[] src, byte[] key, byte[] iv, int mode) 
```
1.密钥是16、24、32字节
2.向量是16字节
