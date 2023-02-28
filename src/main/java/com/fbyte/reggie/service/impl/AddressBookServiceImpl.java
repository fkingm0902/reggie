package com.fbyte.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbyte.reggie.entity.AddressBook;
import com.fbyte.reggie.mapper.AddressBookMapper;
import com.fbyte.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author FHJ
 * @version 1.0
 * @description 地址簿
 * @className AddressBookServiceImpl
 * @date 2023/2/27 27
 * @since 1.0
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
