package com.kevin.snake.bootlicense.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName: StringToFile
 * @Description: 把字符串转换为File并保存在指定目录
 * @author: Liu.Dun
 * @date: 2016年7月18日 下午9:12:16
 */
@Slf4j
public class StringToFile {

    public static boolean string2File(String res) {
        boolean processflag = true;
        String path = ClassPathResourceURI.getResourceURI("/").getPath();
        FileOutputStream fos = null;

        File txt = new File(path + FileNames.LICENSE_FILE_NAME);
        try {
            if (!txt.exists()) {
                txt.createNewFile();
            }
            byte bytes[] = new byte[1024];
            bytes = res.getBytes();
            int b = res.length();
            fos = new FileOutputStream(txt);
            fos.write(bytes, 0, b);
        } catch (IOException e) {
            processflag = false;
            log.error("生成加密序列号有问题:{}", e);
            return processflag;
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                log.error("exception:{}", e);
            }
        }
        return processflag;
    }
}
