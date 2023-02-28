package com.fbyte.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fbyte.reggie.dto.DishDto;
import com.fbyte.reggie.entity.Dish;

import java.util.List;

/**
 * @author FHJ
 * @version 1.0
 * @description 菜品
 * @className DishService
 * @date 2023/2/24 24
 * @since 1.0
 */
public interface DishService extends IService<Dish>{


    /**
     * 新增菜品，同时插入菜品对应的口味数据
     * @param dishDto
     */
    public void saveWithFlavor(DishDto dishDto);

    /**
     * 根据id查询菜品和口味信息
     * @param id
     * @return
     */
    public DishDto getByIdWithFlavor(Long id);

    /**
     * 修改菜品和口味信息
     * @param dishDto
     */
    public void updateWithFlavor(DishDto dishDto);

    /**
     * 删除菜品和口味信息
     * @param ids
     */
    public void removeWithFlavor(List<Long>ids);
}
