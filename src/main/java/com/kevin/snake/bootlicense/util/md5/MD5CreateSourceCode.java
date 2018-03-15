package com.kevin.snake.bootlicense.util.md5;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: MD5CreateSourceCode
 * @Description: 采用字典映射法生成原始序列号, 然后在用MD5加密
 * 唯一优点:比较短
 * 缺点1:生成采用字典映射,最大日期只能选到2062年
 * 2:安全性超级差,序列号有规律,可被反向破解出序列号包含的信息
 * 3:还需要用MD5再加密一次,麻烦
 * @author: Liu.Dun
 * @date: 2016年7月10日 下午8:29:44
 */
public class MD5CreateSourceCode {

    /**
     * 映射模版,可自行定义,也可从外部文件读取
     */
    final static String TEMPLATE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String createSourceCode(String duedate) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    //获取当前时间
        String current = sdf.format(date);

        StringBuffer sb = new StringBuffer();

        sb.append(dateToString(current));

        sb.append("-");

        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            int number2 = random.nextInt(62);
            char a2 = TEMPLATE.charAt(number2);
            sb.append(a2);
        }
        sb.append("-");
        for (int i = 1; i <= 4; i++) {
            int number3 = random.nextInt(62);
            char a3 = TEMPLATE.charAt(number3);
            sb.append(a3);
        }
        sb.append("-");

        sb.append(dateToString(duedate));
        System.out.println(sb.toString());
        System.out.println(sb.length());
        return sb.toString();
    }

    /**
     * @param date
     * @return
     * @param:
     * @return: String
     * @Title: dateToString
     * @Description: 把类似"2017-09-08"这样的字符串拆成"20-17-09-08",转成数组{20,17,09,08},然后把数组中的四个数字分别映射成字符
     * 需要注意:假如年份超过了2062年,那么可能会产生异常,因为模版只有62个字符
     */
    static String dateToString(String date) {
        StringBuffer sb = new StringBuffer(date);
        StringBuffer sb2 = new StringBuffer();
        sb.insert(2, '-');
        String[] str = sb.toString().split("-");
        for (int j = 0; j < str.length; j++) {
            int number = Integer.parseInt(str[j]);
            char a1 = TEMPLATE.charAt(number);
            sb2.append(a1);
        }
        return sb2.toString();
    }
}
