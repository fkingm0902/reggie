package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.entity.DishFlavor;
import com.fbyte.reggie.mapper.DishFlavorMapper;
import com.fbyte.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author FHJ
 * @version 1.0
 * @description 菜品口味
 * @className DishFlavorServiceImpl
 * @date 2023/2/24 24
 * @since 1.0
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper,DishFlavor> implements DishFlavorService {
}
