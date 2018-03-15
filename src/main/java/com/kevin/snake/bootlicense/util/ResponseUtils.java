package com.kevin.snake.bootlicense.util;

import com.kevin.snake.bootlicense.enums.SysMsgEnum;
import com.kevin.snake.bootlicense.exception.BusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by trylen on 16/7/15.
 */
public class ResponseUtils {

    private ResponseUtils() {

    }

    public static String toJSONString(SysMsgEnum sysMsgEnum) {
        return toJSONString(sysMsgEnum, sysMsgEnum.getMessage());
    }

    public static String toJSONString(Object data) {
        HashMap dataMap = new HashMap();
        dataMap.put("data", data);
        return JsonUtil.toJson(dataMap);
    }

    public static String toJSONString(SysMsgEnum sysMsgEnum, Object message) {
        Map<String, Object> error = new HashMap();
        error.put("code", sysMsgEnum.getCode());
        error.put("message", message);
        //fix FAIL type
        restoreFailEnum();
        HashMap dataMap = new HashMap();
        dataMap.put("error", error);
        return JsonUtil.toJson(dataMap);
    }

    public static String toSuccessJsonString() {
        Map<String, Object> data = new HashMap();
        data.put("code", SysMsgEnum.SUCCESS.getCode());
        data.put("message", SysMsgEnum.SUCCESS.getMessage());
        return JsonUtil.toJson(new HashMap() {{
            put("data", data);
        }});
    }

    public static String toErrorResponse(BusinessException e) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", e.getCode());
        data.put("msg", e.getMessage());
        return JsonUtil.toJson(new HashMap() {{
            put("error", data);
        }});
    }

    private static void restoreFailEnum() {
        SysMsgEnum.FAIL.setCode(1);
        SysMsgEnum.FAIL.setMessage("服务器异常,未知错误");
    }

    public static String toJSONString(BusinessException exception) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", exception.getCode());
        error.put("msg", exception.getMessage());
        return JsonUtil.toJson(new HashMap() {{
            put("error", error);
        }});
    }

    public static String toJsonStringFromMap(Map<String, Object> responseMap) {
        String jsonResponse = JsonUtil.toJson(responseMap);
        return jsonResponse;
    }
}

