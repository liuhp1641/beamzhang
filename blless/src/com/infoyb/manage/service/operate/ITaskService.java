package com.cm.manage.service.operate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.cm.manage.model.member.Cooperation;
import com.cm.manage.model.operate.TaskInfo;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.TaskVO;

public interface ITaskService extends IBaseService{
	public void saveTaskInfo(TaskVO taskVO) throws BusiException, IllegalAccessException, InvocationTargetException;
	public EasyuiDataGridJson queryTaskList(EasyuiDataGrid dg, TaskVO taskVO);
	public List<Map> queryInternalAcount();
	public Map<String,String> updateTaskStatus(String taskId,int status,String operate) throws BusiException;
	public Map<String,String> deleteTask(String taskIdStr) throws BusiException;
	public TaskVO getTaskDetailInfo(String taskId);
	public Map<String,String> startTask(String taskIdStr) throws BusiException;
	public List<LotteryControl> getLotteryList();
	public TaskInfo getTask(String taskId);
	public Map<String,Object> getTaskDetail(String taskId);
	public List<Cooperation> getOperationList();

}
