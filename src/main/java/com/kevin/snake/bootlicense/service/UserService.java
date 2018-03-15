package com.kevin.snake.bootlicense.service;


import com.kevin.snake.bootlicense.pojo.DataTableRequest;
import com.kevin.snake.bootlicense.pojo.User;
import com.kevin.snake.bootlicense.pojo.UserByPage;

import java.util.List;

public interface UserService {

    public List<User> SelectAllUsers();

    public int addUser(User user);

    public UserByPage getUserByPage(DataTableRequest request);

    public int modifyUserByID(User user);

    public int userLogin(User user);

    public String createToken(String username);

}
