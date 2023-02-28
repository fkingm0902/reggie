package com.fbyte.reggie.dto;

import com.fbyte.reggie.entity.Setmeal;
import com.fbyte.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @author FHJ
 * @version 1.0
 * @description 套餐数据传输对象
 * @className SetmealDto
 * @date 2023/2/25 25
 * @since 1.0
 */
@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
