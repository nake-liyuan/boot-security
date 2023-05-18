package com.ly.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 通用返回对象
 * @author: LiYuan
 * @time: 2023/5/16 14:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    /**
     * @description: 成功返回结果
     * @time: 2023/5/16 14:32
     * @param data data 获取的数据
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * @description: 成功返回结果
     * @time: 2023/5/16 14:34
     * @param data 获取的数据
     * @param message 提示信息
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * @description: 失败返回结果
     * @time: 2023/5/16 14:34
     * @param errorCode 错误码
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * @description: 失败返回结果
     * @time: 2023/5/16 14:34
     * @param errorCode 错误码
     * @param message 错误信息
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode,String message) {
        return new CommonResult<T>(errorCode.getCode(), message, null);
    }

    /**
     * @description: 失败返回结果
     * @time: 2023/5/16 14:34
     * @param message 提示信息
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * @description: 失败返回结果
     * @time: 2023/5/16 14:33
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * @description: 参数验证失败返回结果
     * @time: 2023/5/16 14:33
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * @description: 参数验证失败返回结果
     * @time: 2023/5/16 14:33
     * @param message 提示信息
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * @description: 未登录返回结果
     * @time: 2023/5/16 14:39
     * @param data 获取的数据
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * @description: 未授权返回结果
     * @time: 2023/5/16 14:33
     * @param data 获取的数据
     * @author: LiYuan
     * @since JDK 17
     * @return com.ly.common.api.CommonResult<T>
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }


}
