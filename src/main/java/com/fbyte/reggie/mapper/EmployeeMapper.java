package com.fbyte.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fbyte.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author FHJ
 * @version 1.0
 * @description 员工
 * @className EmployeeMapper
 * @date 2023/2/23 23
 * @since 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
