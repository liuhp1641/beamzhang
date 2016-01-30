package com.cm.manage.service.operate;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.operate.TaskExchange;
import com.cm.manage.model.operate.TaskExchangeDetail;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.ExchangeVO;

public interface IActiveService extends IBaseService {
	public void saveTaskExchange(ExchangeVO exchangeVO);
	public void saveTaskExchangeDetail(TaskExchangeDetail taskExchangeDetail);
	public TaskExchange findTaskExchangeById(String taskExchangeId);
	public TaskExchangeDetail findTaskExchangeDetailById(String taskExchangeDetailId);
	public List<TaskExchangeDetail> findExchangeDetailListById(String taskExchangeId);
	public void updateTaskExchange(TaskExchange taskExchange);
	public void updateTaskExchangeDetail(TaskExchangeDetail detail);
	public void editTaskExchange(ExchangeVO exchangeVO) throws BusiException;
	public Map<String,String> editTaskExchangeDetail(ExchangeVO exchangeVO) throws BusiException;
	public EasyuiDataGridJson activeList(EasyuiDataGrid dataGrid,ExchangeVO exchangeVO);
	public Map<String,String> deleteTaskExchange(String taskExchangeId) throws BusiException;
	public Map<String,String> updateTaskExchangeStatus(String activeId, int status,String operate) throws BusiException;
	public EasyuiDataGridJson activeItemList(EasyuiDataGrid dg,ExchangeVO exchangeVO);
	public Map<String,String> saveTaskExchangeDetail(ExchangeVO exchangeVO) throws BusiException;
	public Map<String,String> deleteTaskExchangeDetail(String taskExchangeDetailIdStr) throws BusiException;
	public Map<String, String> startActiveItem(String taskExchangeDetailIdStr) throws BusiException;
	public Map<String, String> updateTaskExchangeDetailStatus(String activeItemId,int status, String operate) throws BusiException;
	public Map<String, String> startActive(String taskExchangeIdStr) throws BusiException; 

}
