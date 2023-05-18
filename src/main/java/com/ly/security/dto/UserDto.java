package com.ly.security.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/16 18:48
 */
@Data
public class UserDto {

    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 启用（0->禁用；1->启用）
     */
    private Integer enabled;
    /**
     * 角色集合
     */
    private List<String> roles;
}
