package com.cm.manage.dao.operate;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.member.Cooperation;
import com.cm.manage.model.operate.TaskInfo;
import com.cm.manage.model.operate.TaskPost;
import com.cm.manage.model.operate.TaskPrefix;
import com.cm.manage.model.operate.TaskResultReward;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.TaskVO;

public interface ITaskDao {
	public void saveTask(TaskInfo task);
	public void saveTaskPrefix(TaskPrefix prefix);
	public void saveTaskPost(TaskPost post);
	public void saveTaskReward(TaskResultReward reward);
	public EasyuiDataGridJson queryTaskList(EasyuiDataGrid dg,TaskVO taskVO);
	public TaskInfo getTask(String taskId);
	public List<Map> queryInternalAcount();
	public void updateTask(TaskInfo task);
	public List<TaskInfo> getTaskInfoList(List<String> taskIdList);
	public void deleteTaskList(List<TaskInfo> taskList);
	public TaskPrefix getTaskPrefix(String taskId);
	public TaskPost getTaskPost(String taskId);
	public TaskResultReward getTaskResultReward(String taskId);
	public List<TaskPrefix> getTaskPrefixList(String taskId);
	public List<TaskPost> getTaskPostList(String taskId);
	public List<TaskResultReward> getTaskResultRewardList(String taskId);
	public Map getTaskDetail(String taskId);
	public void updateTaskList(List<TaskInfo> taskList);
	public void deleteTaskPrefix(String taskId);
	public void deleteTaskPost(String taskId);
	public void deleteTaskReward(String taskId);
	public List<LotteryControl> getLotteryList();
	public List<Cooperation> getCooperationList();
	public Map countTaskInfo(String taskId,Integer funddingType);
}
