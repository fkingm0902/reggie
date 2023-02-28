package com.fbyte.reggie.common;

/**
 * @author FHJ
 * @version 1.0
 * @description 基于ThreadLocal封装的工具类，用于保存和获取当前登录用户id
 * @className BaseContext
 * @date 2023/2/24 24
 * @since 1.0
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
