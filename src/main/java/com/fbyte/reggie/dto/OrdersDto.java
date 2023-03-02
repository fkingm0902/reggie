package com.fbyte.reggie.dto;

import com.fbyte.reggie.entity.OrderDetail;
import com.fbyte.reggie.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author FHJ
 * @version 1.0
 * @description TODO
 * @className OrdersDto
 * @date 2023/3/2 02
 * @since 1.0
 */
@Data
public class OrdersDto extends Orders {
    private List<OrderDetail> orderDetails;
}
