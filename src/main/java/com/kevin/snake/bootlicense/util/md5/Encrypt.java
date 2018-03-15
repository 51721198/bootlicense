package com.kevin.snake.bootlicense.util.md5;

import java.security.MessageDigest;


/**
 * 两次加密：第一次MD5加密，第二次ascii码向右偏移两位
 *
 * @author Liu.Dun
 */
public class Encrypt {

    //偏移量,此处写死了
    static final int offset = 2;

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            //System.out.println(str);


            //第二次加密
            String str1 = new String(str);
            StringBuffer sb = new StringBuffer();

            char[] oricode = str1.toCharArray();
            int leng = oricode.length;
            int[] afcode = new int[leng];
            for (int i = 0; i < leng; i++) {
                afcode[i] = oricode[i] + offset;                //所有字符的ascii码偏移
                char lastcode = (char) afcode[i];
                sb.append(lastcode);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
