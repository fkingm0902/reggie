package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.common.CustomException;
import com.fbyte.reggie.dto.SetmealDto;
import com.fbyte.reggie.entity.Setmeal;
import com.fbyte.reggie.entity.SetmealDish;
import com.fbyte.reggie.mapper.SetmealMapper;
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
 * @description 套餐
 * @className SetmealService
 * @date 2023/2/24 24
 * @since 1.0
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;


    /**
     * 根据id查询套餐和关联菜品
     *
     * @param id
     * @return
     */
    @Override
    public SetmealDto getByIdWithDish(Long id) {
        //查询套餐信息
        Setmeal setmeal = this.getById(id);

        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);

        //从setmeal_dish查询出关联的菜品信息
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, id);
        List<SetmealDish> list = setmealDishService.list(queryWrapper);
        setmealDto.setSetmealDishes(list);
        return setmealDto;
    }

    /**
     * 新增套餐同时需要保存套餐和菜品之间的关系
     *
     * @param setmealDto
     */
    @Transactional
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息 setmeal表
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联关系,setmeal_dish
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时删除关联的菜品数据
     *
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态，看是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        int count = this.count(queryWrapper);

        if (count > 0) {
            //如果不能删除跑出一个业务异常
            throw new CustomException("套餐正在售卖中,不能删除");
        }
        //如果可以删除，先删除套餐中的信息
        this.removeByIds(ids);

        //删除关联的菜品中的数据
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }

    /**
     * 修改套餐和关联的菜品信息
     *
     * @param setmealDto
     */
    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        //更新setmeal
        this.updateById(setmealDto);

        //清理关联的菜品信息setmeal_dish
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, setmealDto.getId());

        setmealDishService.remove(queryWrapper);

        //添加关联的菜品信息setmeal_dish
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 根据套餐id修改售卖状态
     * @param status
     * @param ids
     */
    @Override
    public void updateSetmealStatusById(Integer status,  List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(ids !=null,Setmeal::getId,ids);
        List<Setmeal> list = this.list(queryWrapper);

        for (Setmeal setmeal : list) {
            if (setmeal != null){
                setmeal.setStatus(status);
                this.updateById(setmeal);
            }
        }
    }
}
