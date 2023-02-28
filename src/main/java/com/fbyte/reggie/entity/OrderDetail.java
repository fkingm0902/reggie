package com.fbyte.reggie.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author FHJ
 * @version 1.0
 * @description 订单明细
 * @className OrderDetail
 * @date 2023/2/27 27
 * @since 1.0
 */
@Data
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 2977503366668487996L;
    private Long id;

    //名称
    private String name;

    //订单id
    private Long orderId;


    //菜品id
    private Long dishId;


    //套餐id
    private Long setmealId;


    //口味
    private String dishFlavor;


    //数量
    private Integer number;

    //金额
    private BigDecimal amount;

    //图片
    private String image;
}

