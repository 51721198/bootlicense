package com.kevin.snake.bootlicense.util.rsa;


import com.kevin.snake.bootlicense.util.ByteArrayToObj;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.Key;

public class RSAdoEncrypt {

    /**
     * RSA加密方法:优点:  1.安全性非常高
     * 2.不依赖原始序列号,因为发布的是加密后的序列号,
     * 3.一对公私钥可以加密很多序列号,而且即使原始序列号相同,加密公钥相同,加密后的序列号也不相同
     * 唯一缺点:生成的加密序列号较长
     *
     * @param source 将要被加密的字符串
     * @return Sring b1 加密后的字符串
     * @throws Exception
     */

    private static final String ALGORITHM = "RSA";

    public static String encrypt(String source, byte[] publickey) throws Exception {

        String path = Thread.currentThread().getContextClassLoader().getResource("/").toURI().getPath();
        Key publicKey = null;

        publicKey = (Key) ByteArrayToObj.ByteToObject(publickey);

        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes("UTF-8");

        /** 执行加密操作*/
        byte[] b1 = cipher.doFinal(b);

        String encryptedcode = Base64.encodeBase64String(b1);
        return encryptedcode;
    }
}
