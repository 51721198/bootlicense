package com.kevin.snake.bootlicense.util.rsa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RSACreateSourceCode {
    public static String createSourceCode(String duedate, String hospitalName) {
        StringBuffer sb = new StringBuffer();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    //获取当前时间
        String current = sdf.format(date);
        sb.append(current);
        sb.append(duedate);
        sb.append(hospitalName);
        return sb.toString();
    }
}
