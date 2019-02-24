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
     * @param iv 向量
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] processEde(byte[] src, byte[] key, byte[] iv, int mode)
```
1.密钥必须是16或24字节  
2.向量必须是8字节
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
1.密钥可以是16、24、32字节  
2.向量必须是16字节  
3.比des更安全的算法，建议使用
## Rsa
### 生成密钥对
``` java
    /**
     * 产生密钥对
     *
     * @param keyLength 密钥长度，小于1024长度的密钥已经被证实是不安全的，通常设置为1024或者2048，建议2048
     * @return 密钥对
     */
    public static KeyPair generateKeyPair(int keyLength)
```
1.keyPair包括公钥和私钥，建议使用2048长度  
2.可以使用keyPair.getPublic().getEncoded()方法，并base64处理转成字符串形式的公私钥  
3.私钥加密的只能公钥解密  
4.公钥加密的只能私钥解密
### 字符串的公钥、私钥转换成对象
``` java
    /**
     * 将字符串形式的私钥，转换为私钥对象
     *
     * @param privateKeyStr 私钥字符串
     * @return 私钥
     */
    public static PrivateKey key2PrivateKey(String privateKeyStr)
    
    /**
     * 将字符串形式的公钥转换为公钥对象
     *
     * @param publicKeyStr 公钥字符串
     * @return 公钥
     */
    public static PublicKey key2PublicKey(String publicKeyStr)
```
### rsa加解密
``` java
    /**
     * 单次加密的源数据最大长度与密钥长度相关
     * 比如key的长度是2048位，也就是256字节，则加密数据最大245字节，解密数据最大256字节
     * 参考下面的分段加密方法
     *
     * @param src  源数据
     * @param key  公钥或者私钥
     * @param mode 加密：Cipher.ENCRYPT_MODE
     *             解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] process(byte[] src, Key key, int mode)
```
1.rsa单次可加解密的源数据长度和密钥长度相关。例如2048长度的密钥，单次可以加密245字节的数据或解密256字节的数据  
2.如果需要处理超出长度的数据，可以使用下面的分段加密
### rsa分段加解密
``` java
    /**
     * 分段加密方法，自动拆分成合适长度的数据
     *
     * @param src       源数据
     * @param key       公钥或者私钥
     * @param keyLength 秘钥长度
     * @param mode      加密：Cipher.ENCRYPT_MODE
     *                  解密：Cipher.DECRYPT_MODE
     * @return 加解密后的byte数组
     */
    public static byte[] processSection(byte[] src, Key key, int keyLength, int mode)
```
## sm2
### 待整理
