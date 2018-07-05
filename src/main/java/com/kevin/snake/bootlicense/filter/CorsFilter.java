package com.kevin.snake.bootlicense.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Order(1)
@WebFilter(filterName = "corsFilter", urlPatterns = "/*")
@Slf4j
public class CorsFilter implements Filter {

    @Value("${allowOrigin}")
    private String allowOrigin;    //允许访问的客户端域名,例如www.douyu.com,若为*,则表示从任意域都能访问,即不做任何限制
    private String allowMethods = "GET,POST,PUT,DELETE,OPTIONS";   //允许访问的方法名,多个方法名用逗号分隔,比如:GET,POST,PUT
    private String allowCredentials = "true";  //是否允许请求带有验证信息,若要获取客户端域下的cookie时,需要将其设置为true
    private String allowHeaders = "Content-Type,X-Token";     //允许服务端访问的客户端请求头,多个请求头用逗号分隔,例如:Content-type,X-Token
    private String exposeHeaders;     //允许客户端访问的服务端响应头,多个响应头用逗号分割

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        allowOrigin = filterConfig.getInitParameter("allowOrigin");   //filterConfig即为web.xml中对应filter的配置信息
//        allowMethods = filterConfig.getInitParameter("allowMethods");
//        allowCredentials = filterConfig.getInitParameter("allowCredentials");
//        allowHeaders = filterConfig.getInitParameter("allowHeaders");
//        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;       //将servletRequest强制转换为httpServletRequest
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (StringUtils.isNotBlank(allowOrigin)) {
            List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));   //允许的客户端域名放入一个列表中,以逗号分隔,破解不允许指定多个域名的限制
            if (allowOriginList.size() != 0) {
                String currentOrigin = request.getHeader("Origin");
                log.info("currentOrigin is:" + currentOrigin);
                if (allowOriginList.contains(currentOrigin)) {  //如果请求的域名在允许列表当中,那就给请求加一个跨域的请求头
                    log.info("cros can here!!!!!!!!");
                    log.info(currentOrigin);
                    response.setHeader("Access-Control-Allow-Origin", currentOrigin);
                }
            }
        }
        if (StringUtils.isNotEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtils.isNotEmpty(allowCredentials)) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtils.isNotEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtils.isNotEmpty(exposeHeaders)) {
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
