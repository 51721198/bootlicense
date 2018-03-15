package com.kevin.snake.bootlicense.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class ByteArrayToObj {
    private static final Logger LOGGER = LoggerFactory.getLogger(ByteArrayToObj.class);

    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            LOGGER.error("exception:{}", e);
        }
        return obj;
    }
}
