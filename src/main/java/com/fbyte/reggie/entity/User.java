package com.fbyte.reggie.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author FHJ
 * @version 1.0
 * @description 用户类
 * @className User
 * @date 2023/2/25 25
 * @since 1.0
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 3808439751272740012L;
    private Long id;


    //姓名
    private String name;


    //手机号
    private String phone;


    //性别 0 女 1 男
    private String sex;


    //身份证号
    private String idNumber;


    //头像
    private String avatar;


    //状态 0:禁用，1:正常
    private Integer status;
}
