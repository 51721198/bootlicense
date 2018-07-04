package com.kevin.snake.bootlicense.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Slf4j
public class ObjToFile {
    public static boolean object2File(Object object) {
        boolean processflag = true;
        String path = ClassPathResourceURI.getResourceURI("/").getPath();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path + File.separator + FileNames.PRIVATEKEY_NAME));
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            log.error(e + "生成私钥有问题!");
            processflag = false;
            return processflag;
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                log.error("exception:{}", e);
            }
        }
        return processflag;
    }
}
