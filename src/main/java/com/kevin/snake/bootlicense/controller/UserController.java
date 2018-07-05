package com.kevin.snake.bootlicense.controller;

import com.alibaba.fastjson.JSON;
import com.kevin.snake.bootlicense.enums.ProcessResultEnum;
import com.kevin.snake.bootlicense.pojo.DataTableRequest;
import com.kevin.snake.bootlicense.pojo.ProcessResult;
import com.kevin.snake.bootlicense.pojo.User;
import com.kevin.snake.bootlicense.pojo.UserByPage;
import com.kevin.snake.bootlicense.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "showAllUsers", method = RequestMethod.GET)
    public List<User> showAllUsers() {

        List<User> users = new ArrayList<User>();
        try {
            users = userService.SelectAllUsers();
        } catch (Exception e) {
            log.error("exception:{}", e);
        }
        return users;
    }

    @RequestMapping(value = "getUserByPage", method = RequestMethod.POST)
    public UserByPage getUserByPage(HttpServletRequest request) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        UserByPage result = new UserByPage();
        try {
            dataTableRequest.setDraw(Integer.parseInt(request.getParameter("draw")));
            dataTableRequest.setLength(Integer.parseInt(request.getParameter("length")));
            dataTableRequest.setStart(Integer.parseInt(request.getParameter("start")));
            dataTableRequest.setObject(null);
            if (dataTableRequest != null) result = userService.getUserByPage(dataTableRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(JSON.toJSONString(result));
        return result;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        int res = 0;
        log.info("+++++" + user.getUsername());
        try {
            res = userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping(value = "modifyUser", method = RequestMethod.POST)
    public int modifyUser(@RequestBody User user) {
        int res = 0;
        try {
            res = userService.modifyUserByID(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @CrossOrigin
    @RequestMapping(value = "login")
    public ProcessResult userLogin(@RequestBody User user) {
        ProcessResult<String> result = new ProcessResult<>();
        if (user == null){
            result.setResultcode(-1);
            return result;
        }
        int usergroup;
        usergroup = userService.userLogin(user);
        if (usergroup == -1){
            result.setResultcode(-1);
            result.setResult(ProcessResultEnum.LOGIN_FAIL);
            return result;
        }
        result.setResultcode(usergroup);
        result.setResult(ProcessResultEnum.SUCCESS, userService.createToken(user.getUsername()));
        return result;
    }
}

