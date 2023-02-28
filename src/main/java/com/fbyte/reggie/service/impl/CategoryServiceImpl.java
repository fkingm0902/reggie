package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.common.CustomException;
import com.fbyte.reggie.entity.Category;
import com.fbyte.reggie.entity.Dish;
import com.fbyte.reggie.entity.Setmeal;
import com.fbyte.reggie.mapper.CategoryMapper;
import com.fbyte.reggie.service.CategoryService;
import com.fbyte.reggie.service.DishService;
import com.fbyte.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author FHJ
 * @version 1.0
 * @description 分类
 * @className CategoryServiceImpl
 * @date 2023/2/24 24
 * @since 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联菜品，如关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if (count1 > 0) {
            //已经关联，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品,不能删除");
        }

        //查询当前分类是否关联套餐，如关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            //已经关联，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐,不能删除");
        }

        super.removeById(id);
        //正常删除分类
    }
}
