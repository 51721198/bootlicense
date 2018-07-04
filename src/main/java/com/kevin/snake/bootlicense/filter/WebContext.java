package com.kevin.snake.bootlicense.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class WebContext {

    private static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<>();

    /**
     * 初始化
     */
    public static void init(HttpServletRequest request, HttpServletResponse response) {
        requestHolder.set(request);
        responseHolder.set(response);
    }

    /**
     * 销毁
     */
    public static void destroy() {
        requestHolder.remove();
        responseHolder.remove();
    }

    /**
     * 获取request对象
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try {
            request = requestHolder.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    /**
     * 获取response对象
     */
    public static HttpServletResponse getResponse() {
        return responseHolder.get();
    }

}
