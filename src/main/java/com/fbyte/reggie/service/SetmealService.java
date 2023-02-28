package com.fbyte.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fbyte.reggie.dto.SetmealDto;
import com.fbyte.reggie.entity.Setmeal;

import java.util.List;

/**
 * @author FHJ
 * @version 1.0
 * @description 套餐
 * @className SetmealService
 * @date 2023/2/24 24
 * @since 1.0
 */
public interface SetmealService extends IService<Setmeal> {

    /**
     * 根据id查询套餐和关联菜品
     * @param id
     * @return
     */
    public SetmealDto getByIdWithDish(Long id);

    /**
     * 新增套餐同时需要保存套餐和菜品之间的关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时删除关联的菜品数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);


    /**
     * 修改套餐和关联的菜品信息
     * @param setmealDto
     */
    public void updateWithDish(SetmealDto setmealDto);

    /**
     * 根据套餐id修改售卖状态
     * @param status
     * @param ids
     */
    public void updateSetmealStatusById(Integer status,List<Long> ids);
}
