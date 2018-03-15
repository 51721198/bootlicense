package com.kevin.snake.bootlicense.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.snake.bootlicense.enums.SysMsgEnum;
import com.kevin.snake.bootlicense.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtil() {

    }

    /**
     * 把对象转换为json
     * author：Lizhao
     * Date:2017/4/10.
     * version:1.0
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        String json = null;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.warn("convert POJO to JSON failure", e);
            throw new BusinessException(SysMsgEnum.JSON_CONVERT_ERROR);
        }
        return json;
    }

    /**
     * 把json转换成pojo
     * author：Lizhao
     * Date:2017/4/10.
     * version:1.0
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo = null;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOGGER.warn("convert JSON to POJO failure", e);
            throw new BusinessException(SysMsgEnum.JSON_CONVERT_ERROR);
        }
        return pojo;
    }
}

