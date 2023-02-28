package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.entity.Employee;
import com.fbyte.reggie.mapper.EmployeeMapper;
import com.fbyte.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author FHJ
 * @version 1.0
 * @description TODO
 * @className EmployeeServiceImpl
 * @date 2023/2/23 23
 * @since 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
