package com.kevin.snake.bootlicense.dao;

import com.kevin.snake.bootlicense.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> selectAllUsers();

    int addUser(User user);

    List<User> getUserByPage(@Param("length") Integer length, @Param("start") Integer start);

    Integer getTotal();

    int modifyUserByID(User user);

    User getUserByName(String username);
}
