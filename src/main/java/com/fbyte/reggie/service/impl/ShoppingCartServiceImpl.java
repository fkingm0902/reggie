package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.entity.ShoppingCart;
import com.fbyte.reggie.mapper.ShoppingCartMapper;
import com.fbyte.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @author FHJ
 * @version 1.0
 * @description TODO
 * @className ShoppingCartServiceImpl
 * @date 2023/2/27 27
 * @since 1.0
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
