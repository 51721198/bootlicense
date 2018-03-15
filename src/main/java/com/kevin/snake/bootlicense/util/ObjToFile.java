package com.kevin.snake.bootlicense.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjToFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjToFile.class);

    public static boolean object2File(Object object) {
        boolean processflag = true;
        String path = ClassPathResourceURI.getResourceURI("/").getPath();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path + File.separator + FileNames.PRIVATEKEY_NAME));
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            LOGGER.error(e + "生成私钥有问题!");
            processflag = false;
            return processflag;
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                LOGGER.error("exception:{}", e);
            }
        }
        return processflag;
    }
}
