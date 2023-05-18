package com.ly.service;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/17 4:32
 */
public interface AuthService {

    /**
     * @description:
     * @time: 2023/5/17 4:36
     * @param username 用户名
     * @param password 密码
     * @author: LiYuan
     * @since JDK 17
     * @return 生成的JWT的token
     */
    String login(String username,String password);
}
