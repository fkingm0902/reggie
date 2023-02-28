package com.fbyte.reggie.controller;

import com.fbyte.reggie.common.R;
import com.fbyte.reggie.entity.Orders;
import com.fbyte.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FHJ
 * @version 1.0
 * @description 订单管理
 * @className OrdersController
 * @date 2023/2/27 27
 * @since 1.0
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){

        log.info("订单数据：{}",orders);
        ordersService.submit(orders);

        return R.success("下单成功");
    }
}
