package com.kevin.snake.bootlicense.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class ObjToByteArray {

    private final static Logger LOGGER = LoggerFactory.getLogger(ObjToByteArray.class);

    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            LOGGER.error("exception:{}", e);
        }
        return bytes;
    }
}