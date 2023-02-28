package com.fbyte.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fbyte.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author FHJ
 * @version 1.0
 * @description 购物车
 * @className ShoppingCartMapper
 * @date 2023/2/27 27
 * @since 1.0
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
