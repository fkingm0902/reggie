package com.fbyte.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fbyte.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author FHJ
 * @version 1.0
 * @description 菜品
 * @className DishMapper
 * @date 2023/2/24 24
 * @since 1.0
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
