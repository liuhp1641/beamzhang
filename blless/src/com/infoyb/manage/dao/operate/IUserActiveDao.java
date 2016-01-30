package com.cm.manage.dao.operate;

import java.util.Map;

import com.cm.manage.model.operate.TaskUserExchange;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.UserTaskExchangeVO;

public interface IUserActiveDao {
	public EasyuiDataGridJson userActiveList(EasyuiDataGrid dg,UserTaskExchangeVO exchangeVO);
	public EasyuiDataGridJson userActiveDetailList(EasyuiDataGrid dg,String userExchangeId);
	public Map<String,String> count(String userExchangeId);
	public TaskUserExchange findTaskUserExchangeById(String userExchangeId);
	public Map<String, String> count(UserTaskExchangeVO exchangeVO);
}
