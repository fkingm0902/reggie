package com.fbyte.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fbyte.reggie.entity.Category;

/**
 * @author FHJ
 * @version 1.0
 * @description 分类
 * @className CategoryService
 * @date 2023/2/24 24
 * @since 1.0
 */
public interface CategoryService extends IService<Category>{

    public void remove(Long id);
}
