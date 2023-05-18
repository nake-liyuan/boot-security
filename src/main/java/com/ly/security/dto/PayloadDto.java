package com.ly.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/16 22:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class PayloadDto {
    /**
     * 主题
     */
    private String sub;
    /**
     * 签发时间
     */
    private Long iat;
    /**
     * 过期时间
     */
    private Long exp;
    /**
     * JWT的ID
     */
    private String jti;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户拥有的权限
     */
    private List<String> authorities;


}
