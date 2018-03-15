package com.kevin.snake.bootlicense.util.rsa;


/**
 * @ClassName: RSAGetDueDate
 * @Description: RSA解密后从序列号中获取到期日期
 * @author: Liu.Dun
 * @date: 2016年7月10日 下午8:22:59
 */
public class RSAGetDueDate {

    public static String getDueDate(String RSAcode) {

        String duedate = RSAcode.substring(18);

        return duedate;
    }
}





