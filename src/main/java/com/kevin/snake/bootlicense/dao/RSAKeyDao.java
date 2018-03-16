package com.kevin.snake.bootlicense.dao;

import com.kevin.snake.bootlicense.pojo.RSAKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RSAKeyDao {

    int insertRSAKeyPair(RSAKey rsakeypair);

    RSAKey selectKeyByID();

}
