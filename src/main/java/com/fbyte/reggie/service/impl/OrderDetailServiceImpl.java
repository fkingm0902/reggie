package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.entity.OrderDetail;
import com.fbyte.reggie.mapper.OrderDetailMapper;
import com.fbyte.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author FHJ
 * @version 1.0
 * @description TODO
 * @className OrderDetailServiceImpl
 * @date 2023/2/27 27
 * @since 1.0
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
