package com.cm.manage.service.operate.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.IUserActiveDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IUserActiveService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.UserTaskExchangeVO;
@Service("userActiveService")
public class UserActiveServiceImpl extends BaseServiceImpl implements IUserActiveService{
	@Autowired
	private IUserActiveDao userActiveDao;

	@Override
	public EasyuiDataGridJson userActiveList(EasyuiDataGrid dg,
			UserTaskExchangeVO exchangeVO) {
		return userActiveDao.userActiveList(dg, exchangeVO);
	}

	@Override
	public EasyuiDataGridJson userActiveDetailList(EasyuiDataGrid dg,
			String userExchangeId) {
		return userActiveDao.userActiveDetailList(dg, userExchangeId);
	}

	@Override
	public Map<String, String> count(String userExchangeId) {
		return userActiveDao.count(userExchangeId);
	}

	@Override
	public Map<String, String> count(UserTaskExchangeVO exchangeVO) {
		return userActiveDao.count(exchangeVO);
	}

}
