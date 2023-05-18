package com.ly.common.api;

/**
 * @description: 封装API的错误码
 * @author: LiYuan
 * @time: 2023/5/16 14:28
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
