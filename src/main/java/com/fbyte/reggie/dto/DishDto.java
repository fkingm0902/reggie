package com.fbyte.reggie.dto;

import com.fbyte.reggie.entity.Dish;
import com.fbyte.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FHJ
 * @version 1.0
 * @description 菜品数据传输对象
 * @className DishDto
 * @date 2023/2/24 24
 * @since 1.0
 */
@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
