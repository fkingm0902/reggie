package com.fbyte.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fbyte.reggie.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author FHJ
 * @version 1.0
 * @description 套餐菜品关系
 * @className SetmealDishMapper
 * @date 2023/2/25 25
 * @since 1.0
 */
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {
}
