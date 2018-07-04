package com.kevin.snake.bootlicense.util.md5;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: GetDuedateFromSource
 * @Description: MD5加密从序列号中获取加密日期
 * @author: Liu.Dun
 * @date: 2016年7月10日 下午8:24:45
 */
@Slf4j
public class GetDuedateFromSource {

    final static String TEMPLATE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getDueDate(String sourceNumber) {

        String date = sourceNumber.substring(15);

        char[] c = date.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < c.length; i++) {
            int j = TEMPLATE.indexOf(c[i]);
            sb.append(j);
        }
        sb.insert(4, '-');

        sb.insert(7, '-');

        log.info(sb.toString());
        return sb.toString();
    }

}
