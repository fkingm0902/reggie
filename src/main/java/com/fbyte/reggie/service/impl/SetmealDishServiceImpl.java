package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.entity.SetmealDish;
import com.fbyte.reggie.mapper.SetmealDishMapper;
import com.fbyte.reggie.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author FHJ
 * @version 1.0
 * @description 套餐菜品关系
 * @className SetmealDishServiceImpl
 * @date 2023/2/25 25
 * @since 1.0
 */
@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
