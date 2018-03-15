package com.kevin.snake.bootlicense.aop;


/**
 * @author Liu.Dun
 *         安全令牌接口,用于检查前端请求是否合法
 */
public interface TokenManager {

    /*生成安全令牌*/
    String createToken(String username);

    //验证安全令牌
    boolean checkToken(String token);

}
