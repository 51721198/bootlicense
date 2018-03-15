package com.kevin.snake.bootlicense.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {

    private String username;
    private Integer userID;
    private String password;
    private Integer usergroup;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(Integer usergroup) {
        this.usergroup = usergroup;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userID=" + userID +
                ", password='" + password + '\'' +
                ", usergroup=" + usergroup +
                '}';
    }
}
