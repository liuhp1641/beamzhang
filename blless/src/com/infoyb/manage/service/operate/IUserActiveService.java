package com.cm.manage.service.operate;

import java.util.Map;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.UserTaskExchangeVO;

public interface IUserActiveService extends IBaseService{
	public EasyuiDataGridJson userActiveList(EasyuiDataGrid dg,UserTaskExchangeVO exchangeVO);
	public EasyuiDataGridJson userActiveDetailList(EasyuiDataGrid dg,String userExchangeId);
	public Map<String,String> count(String userExchangeId);
	public Map<String,String>count(UserTaskExchangeVO exchangeVO);
}
