package com.fbyte.reggie.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FHJ
 * @version 1.0
 * @description 通用的返回结果类,服务端响应结果的数据都会封装成这种对象
 *  private Integer code; //编码：1成功，0和其它数字为失败
 *  private String msg; //错误信息
 *  private T data; //数据
 *  private Map map = new HashMap(); //动态数据
 * @className R
 * @date 2023/2/23 23
 * @since 1.0
 */
@Data
public class R<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    private Map map = new HashMap();

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
