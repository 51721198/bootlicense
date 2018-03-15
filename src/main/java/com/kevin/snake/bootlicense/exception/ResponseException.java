package com.kevin.snake.bootlicense.exception;

import com.alibaba.fastjson.JSON;
import com.kevin.snake.bootlicense.enums.ProcessResultEnum;
import com.kevin.snake.bootlicense.pojo.ProcessResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by liudun on 2017/7/25.
 */
@Component
public class ResponseException extends AbstractHandlerExceptionResolver {


    //这个order决定了这个resovler被调用的次序,值越小越会被优先执行
    private final static int ORDER = 2;

    //设置order值
    public ResponseException(){
        this.setOrder(ORDER);
    }


    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setStatus(SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter pw = null;

        ModelAndView mv = new ModelAndView();

        ProcessResult result = new ProcessResult();
        result.setResultcode(2);
        result.setResult(ProcessResultEnum.QUE_FAIL,"发生了异常");

        //这里可以选择是打出所有的堆栈还是只打出异常携带的消息
        result.setResultmessage(ex.getMessage());

        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pw != null) {
            pw.print(JSON.toJSONString(result));
        }
        if (pw != null) {
            pw.flush();
        }
        return mv;
    }
}
