package com.fbyte.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fbyte.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author FHJ
 * @version 1.0
 * @description 分类
 * @className CategoryMapper
 * @date 2023/2/24 24
 * @since 1.0
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
