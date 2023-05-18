package com.ly.common.constant;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/16 23:58
 */
public interface AuthConstant {

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "blacklist_token:";


}
