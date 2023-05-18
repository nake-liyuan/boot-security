package com.ly.common.exception;

import com.ly.common.api.IErrorCode;

/**
 * @description: 断言处理类，用于抛出各种API异常
 * @author: LiYuan
 * @time: 2023/5/16 15:35
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
