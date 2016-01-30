package com.cm.manage.dao.operate;

import java.util.List;

import com.cm.manage.model.operate.TaskExchange;
import com.cm.manage.model.operate.TaskExchangeDetail;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.ExchangeVO;

public interface IActiveDao {
	public void saveTaskExchange(TaskExchange taskExchange);
	public void saveTaskExchangeDetail(TaskExchangeDetail detail);
	public TaskExchange findTaskExchangeById(String taskExchangeId);
	public TaskExchangeDetail findTaskExchangeDetailById(String taskExchangeDetailId);
	public List<TaskExchangeDetail> findExchangeDetailListById(String taskExchangeId);
	public void updateTaskExchange(TaskExchange taskExchange);
	public void updateTaskExchangeDetail(TaskExchangeDetail detail);
	public EasyuiDataGridJson activeList(EasyuiDataGrid dataGrid,ExchangeVO exchangeVO);
	public void deleteTaskExchangeById(String taskExchangeId);
	public void deleteTaskExchangeDetail(String taskExchangeDetailId);
	public void deleteTaskExchangeItemById(String taskExchangeId);
	public void deleteTaskExchange(TaskExchange taskExchange);
	public void deleteActiveInfo(TaskExchange taskExchange);
	public List<TaskExchange> getTaskExchangeList(List<String> taskExchangeIdList);
	public void deleteTaskExchangeList(List<TaskExchange> taskExchangeList);
	public void deleteTaskExchangeDetailList(List<TaskExchangeDetail> taskExchangeDetailList);
	public EasyuiDataGridJson activeItemList(EasyuiDataGrid dataGrid,ExchangeVO exchangeVO);
	public List<TaskExchangeDetail> getTaskExchangeDetailList(List<String> taskExchangeDetailIdList);
	public void updateTaskExchangeDetailList(List<TaskExchangeDetail> detailList);
	public void updateTaskExchangeList(List<TaskExchange> taskExchangeList);
	public List<TaskExchangeDetail> getNotCurrentDetailList(String taskExchangeId,String taskExchangeDetailId);
	public List<TaskExchangeDetail> getDetailListByStatus(String taskExchangeId,int status);
}
