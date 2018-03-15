package com.kevin.snake.bootlicense.util.rsa;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class RSAKeyPair {

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";
    /**
     * 密钥长度，用来初始化
     */
    private static final int KEYSIZE = 1024;

    /**
     * 生成密钥对
     *
     * @throws Exception
     */
    public static Map<String, Key> generateKeyPair() throws Exception {

        Map<String, Key> map = new HashMap<String, Key>();
        String path = Thread.currentThread().getContextClassLoader().getResource("/").toURI().getPath();
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom secureRandom = new SecureRandom();

        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        keyPairGenerator.initialize(KEYSIZE, secureRandom);
        //keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();
        map.put("publicKey", publicKey);

        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();
        map.put("privateKey", privateKey);

        return map;
    }
}
