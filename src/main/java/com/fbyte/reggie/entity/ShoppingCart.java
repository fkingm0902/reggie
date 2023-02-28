package com.fbyte.reggie.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author FHJ
 * @version 1.0
 * @description 购物车
 * @className ShoppingCart
 * @date 2023/2/27 27
 * @since 1.0
 */
@Data
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1604590082321721541L;private Long id;

    //名称
    private String name;

    //用户id
    private Long userId;

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

    private LocalDateTime createTime;
}
