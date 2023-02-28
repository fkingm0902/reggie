package com.fbyte.reggie.common;

/**
 * @author FHJ
 * @version 1.0
 * @description 自定义业务异常
 * @className CustomException
 * @date 2023/2/24 24
 * @since 1.0
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
