package com.kevin.snake.bootlicense.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: BounceController
 * @Description: 处理页面跳转
 * @author: Liu.Dun
 * @date: 2016年7月5日 下午5:38:47
 */

@RestController
@Slf4j
public class BounceController {

    @Resource
    private Environment env;

    @Resource
    private DiscoveryClient client;

    @RequestMapping(value = "/")
    public String defaultPage() {
        ServiceInstance instance = client.getLocalServiceInstance();
        return JSON.toJSONString("hello,host:"+ instance.getHost() + "  service_id:" + instance.getServiceId()+ "     Ceshiyixiarebel测试页面");
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
