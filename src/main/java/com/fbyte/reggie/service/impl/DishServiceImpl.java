package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.common.CustomException;
import com.fbyte.reggie.dto.DishDto;
import com.fbyte.reggie.entity.Dish;
import com.fbyte.reggie.entity.DishFlavor;
import com.fbyte.reggie.entity.Setmeal;
import com.fbyte.reggie.entity.SetmealDish;
import com.fbyte.reggie.mapper.DishMapper;
import com.fbyte.reggie.service.DishFlavorService;
import com.fbyte.reggie.service.DishService;
import com.fbyte.reggie.service.SetmealDishService;
import com.fbyte.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FHJ
 * @version 1.0
 * @description 菜品
 * @className DishServiceImpl
 * @date 2023/2/24 24
 * @since 1.0
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    DishFlavorService dishFlavorService;

    @Autowired
    SetmealDishService setmealDishService;

    @Autowired
    SetmealService setmealService;

    /**
     * 新增菜品，同时插入菜品对应的口味数据
     *
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {

        //保存基本信息到菜品表dish
        this.save(dishDto);
        //菜品id
        Long dishId = dishDto.getId();

        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }


    /**
     * 根据id查询菜品和口味信息
     *
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {

        // 查询菜品信息，从dish表查
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        // 从dish_flavor查询对应的口味
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    /**
     * 修改菜品和口味信息
     *
     * @param dishDto
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表
        this.updateById(dishDto);

        //清理当前菜品口味对应数据 dish_flavor
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());

        dishFlavorService.remove(queryWrapper);

        //添加当前提交过来的口味信息 dish_flavor

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 删除菜品和口味信息
     * 1.判断要删除的菜品在不在售卖的套餐中，如果在那不能删除
     * 2.要先判断要删除的菜品是否在售卖，如果在售卖也不能删除
     *
     * @param ids
     */
    @Override
    public void removeWithFlavor(List<Long> ids) {
        //先查询该菜品是否在售卖，如果是则抛出业务异常
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId, ids);
        queryWrapper.eq(Dish::getStatus, 1);
        int count = this.count(queryWrapper);

        if (count > 0) {
            //如果不能删除跑出一个业务异常
            throw new CustomException("菜品正在售卖中,不能删除");
        }

        //根据菜品id在stemeal_dish表中查出哪些套餐包含该菜品
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getDishId, ids);
        List<SetmealDish> list = setmealDishService.list(setmealDishLambdaQueryWrapper);
        if (list.size() != 0){
            List<Setmeal> setmeals=
            list.stream().map((item) ->{
                Long setmealId = item.getSetmealId();
                LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
                setmealQueryWrapper.eq(Setmeal::getId,setmealId);
                setmealQueryWrapper.eq(Setmeal::getStatus,1);
                Setmeal setmeal = setmealService.getOne(setmealQueryWrapper);
                return setmeal;
            }).collect(Collectors.toList());
            if (setmeals.size() != 0){
                throw new CustomException("菜品关联的套餐正在在售卖中,不能删除");
            }
        }
        super.removeByIds(ids);
        //删除菜品对应的口味  也是逻辑删除
        LambdaQueryWrapper<DishFlavor> flavorQueryWrapper = new LambdaQueryWrapper<>();
        flavorQueryWrapper.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(flavorQueryWrapper);
    }
}
