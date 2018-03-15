package com.kevin.snake.bootlicense.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: BounceController
 * @Description: 处理页面跳转
 * @author: Liu.Dun
 * @date: 2016年7月5日 下午5:38:47
 */

@RestController
@RequestMapping(value = "/")
public class BounceController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BounceController.class);

    @Autowired
    private Environment env;

    @RequestMapping(value = "/")
    public String defaultPage() {
        return JSON.toJSONString(env.getProperty("master") + " : " + env.getProperty("masterpass") + "   Ceshiyixiarebel测试页面");
    }

    @RequestMapping(value = "tocreatecode")
    public String toCreate(HttpServletRequest request) {
        return "creatcode";
    }

    @RequestMapping(value = "toshowallcodes")
    public String showAll(HttpServletRequest request, HttpServletResponse response) {
        return "showallcodes";
    }


    @RequestMapping(value = "toshowallhospital")
    public String hospital() {
        return "showallhospitals";
    }

    @RequestMapping(value = "toaddhospital")
    public String toAdd() {
        return "addhospital";
    }

    @RequestMapping(value = "exception",method = RequestMethod.GET)
    public String testException() throws Exception {
        throw new Exception("controller exception");
    }
}
