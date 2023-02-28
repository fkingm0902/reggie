package com.fbyte.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fbyte.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author FHJ
 * @version 1.0
 * @description 用户
 * @className UserMapper
 * @date 2023/2/27 27
 * @since 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
