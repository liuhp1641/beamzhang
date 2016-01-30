package com.cm.manage.service.base.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.service.base.IBaseService;

/**
 * 基础Service,所有ServiceImpl需要extends此Service来获得默认事务的控制
 * 
 * @author 
 * 
 */
@Service("baseService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BaseServiceImpl implements IBaseService {
}
