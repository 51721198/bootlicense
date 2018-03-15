package com.kevin.snake.bootlicense.util.rsa;

import com.kevin.snake.bootlicense.util.FileNames;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.Key;

public class RSAdoDecrypt {

    /**
     * 解密算法
     *
     * @param cryptograph
     * 密文
     * @return
     * @throws Exception
     */
    private static final String ALGORITHM = "RSA";
    private static final Logger LOGGER = LoggerFactory.getLogger(RSAdoDecrypt.class);

    public static String decrypt(String cryptograph) throws Exception {
        Key privateKey;
        String path = Thread.currentThread().getContextClassLoader().getResource("/").toURI().getPath();
        ObjectInputStream ois = null;
        try {
            /** 将文件中的私钥对象读出 */
            ois = new ObjectInputStream(new FileInputStream(path + FileNames.PRIVATEKEY_NAME));
            privateKey = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        } finally {
            ois.close();
        }

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        /** 执行解密操作 */
        byte[] b1 = Base64.decodeBase64(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }
}
