package com.fbyte.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fbyte.reggie.entity.Orders;

/**
 * @author FHJ
 * @version 1.0
 * @description TODO
 * @className OrdersService
 * @date 2023/2/27 27
 * @since 1.0
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);
}
