package com.fbyte.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fbyte.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author FHJ
 * @version 1.0
 * @description 订单
 * @className OrdersMapper
 * @date 2023/2/27 27
 * @since 1.0
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
