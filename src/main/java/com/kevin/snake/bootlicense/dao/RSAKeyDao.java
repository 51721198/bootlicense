package com.kevin.snake.bootlicense.dao;

import com.kevin.snake.bootlicense.pojo.RSAKey;
import org.springframework.stereotype.Component;

@Component
public interface RSAKeyDao {

    int insertRSAKeyPair(RSAKey rsakeypair);

    RSAKey selectKeyByID();

}
