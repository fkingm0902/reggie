package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.entity.User;
import com.fbyte.reggie.mapper.UserMapper;
import com.fbyte.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author FHJ
 * @version 1.0
 * @description 用户
 * @className UserServiceImpl
 * @date 2023/2/27 27
 * @since 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
