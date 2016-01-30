package com.cm.manage.dao.operate.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.ITaskDao;
import com.cm.manage.model.member.Cooperation;
import com.cm.manage.model.operate.TaskInfo;
import com.cm.manage.model.operate.TaskPost;
import com.cm.manage.model.operate.TaskPrefix;
import com.cm.manage.model.operate.TaskResultReward;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.TaskVO;

@Repository("taskInfoDao")
public class TaskDaoImpl implements ITaskDao {

	@Autowired
	private IBaseDao<TaskInfo> taskDao;
	@Autowired
	private IBaseDao<TaskPrefix> taskPrefixDao;
	@Autowired
	private IBaseDao<TaskPost> taskPostDao;
	@Autowired
	private IBaseDao<TaskResultReward> taskResultRewardDao;
	@Autowired
	private IBaseDao<Map<String,Object>> taskMapDao;
	@Autowired
	private IBaseDao<Map<String,Object>> accountDao;
	@Autowired 
	private IBaseDao<LotteryControl> lotteryDao;
	@Autowired
	private IBaseDao<Cooperation> cooperationDao;
	@Override
	public void saveTask(TaskInfo task) {
		taskDao.save(task);
	}
	@Override
	public void saveTaskPost(TaskPost post) {
		taskPostDao.save(post);
		
	}
	@Override
	public void saveTaskPrefix(TaskPrefix prefix) {
		taskPrefixDao.save(prefix);
		
	}
	@Override
	public void saveTaskReward(TaskResultReward reward) {
		taskResultRewardDao.save(reward);
		
	}
	@Override
	public EasyuiDataGridJson queryTaskList(EasyuiDataGrid dg, TaskVO taskVO) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select t.task_id as taskId,t.name as name,t.describe as describe,");
		sb.append("t.datelimit_desc as dateLimitDesc,t.start_time as startTime,t.end_time as endTime,t.status as status,");
		sb.append("t.task_order as taskOrder,pre.prefix_task_id as prefixTaskId,");
		sb.append("(select t2.name from task_info t2 where t2.task_id = pre.prefix_task_id) as prefixTaskName,");
		sb.append("r.amount as rewardAmount,r.out_user_code as outUserCode,r.funding_type as fundingType from task_info t left join ");
		sb.append("task_prefix_condition pre on t.task_id=pre.task_id left join task_result_reward r on t.task_id=r.task_id ");
		sb.append("where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(taskVO != null){
			boolean flag = taskVO.getFlag();
			if(CommonUtil.isNotEmpty(taskVO.getTaskId())){
				 if (flag) {
	                    sb.append(" and t.task_id like ? ");
	                    param.add("%" + taskVO.getTaskId() + "%");
	                } else {
	                    sb.append(" and t.task_id = ? ");
	                    param.add(taskVO.getTaskId());
	                }
			}
			if(CommonUtil.isNotEmpty(taskVO.getName())){
				 if (flag) {
	                    sb.append(" and t.name like ? ");
	                    param.add("%" + taskVO.getName() + "%");
	                } else {
	                    sb.append(" and t.name = ? ");
	                    param.add(taskVO.getName());
	                }
			}
			 if (CommonUtil.isNotEmpty(taskVO.getStatus())) {
	                sb.append(" and t.status = ?");
	                param.add(taskVO.getStatus());
	          }
			 
			 String fromSeconds = " 00:00:00";
			 String toSeconds = " 23:59:59";
			
			if (CommonUtil.isNotEmpty(taskVO.getStartFromDate())) {
                sb.append(" and t.start_time >= ?");
                param.add(DateUtil.format(taskVO.getStartFromDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(taskVO.getStartToDate())) {
                sb.append(" and t.start_time <= ?");
                param.add(DateUtil.format(taskVO.getStartToDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
            }

            if (CommonUtil.isNotEmpty(taskVO.getEndFromDate())) {
                sb.append(" and t.end_time >= ?");
                param.add(DateUtil.format(taskVO.getEndFromDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(taskVO.getEndToDate())) {
                sb.append(" and t.end_time <= ?");
                param.add(DateUtil.format(taskVO.getEndToDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
            }
			
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(taskMapDao.countBySql(totalHql,param).longValue());
		sb.append(" order by t.create_time desc ");
		List<Map> taskInfoList = taskMapDao.findBySql(sb.toString(),param,dg.getPage(),dg.getRows());
		List<Map> allAcount = queryInternalAcount();
		int accountSize = 0;
		if(allAcount != null && allAcount.size() > 0){
			accountSize = allAcount.size();
		}
		List<TaskVO> taskVOList = new ArrayList<TaskVO>();
		if(taskInfoList != null && taskInfoList.size() > 0){
			for(Map map : taskInfoList){
				TaskVO task = new TaskVO();
				task.setName((String)map.get("NAME"));
				task.setTaskId((String)map.get("TASKID"));
				task.setDateLimitDesc((String)map.get("DATELIMITDESC"));
				task.setDescribe((String)map.get("DESCRIBE"));
				Date startTime = (Date) map.get("STARTTIME");
				task.setStartTime(DateUtil.format(startTime));
				if(map.get("ENDTIME") != null){
					Date endTime = (Date) map.get("ENDTIME");
					task.setEndTime(DateUtil.format(endTime));
				}
				task.setStatus(CommonUtil.formatDouble(map.get("STATUS")).intValue());
				task.setTaskOrder(CommonUtil.formatDouble(map.get("TASKORDER")).intValue());
				task.setPrefixTaskId((String)map.get("PREFIXTASKID"));
				if(CommonUtil.isNotEmpty(map.get("PREFIXTASKNAME"))){
					task.setPrefixTaskName((String)map.get("PREFIXTASKNAME"));
				}else{
					task.setPrefixTaskName("无");
				}
				String userCode = (String)map.get("OUTUSERCODE");
				task.setOutUserCode(userCode);
				int fundingType = 0;
				if(CommonUtil.isNotEmpty(map.get("FUNDINGTYPE"))){
					fundingType = CommonUtil.formatDouble(map.get("FUNDINGTYPE")).intValue();
				}
				Double rewardAmount = CommonUtil.formatDouble(map.get("REWARDAMOUNT")).doubleValue();
				task.setRewardAmount(rewardAmount);
				if(CommonConstants.FUNDINGTYPE_PRESIENT_FLAG == fundingType){
					task.setRewardAmountText(CommonBusiUtil.formatAmount(rewardAmount));
				}else{
					task.setRewardAmountText(CommonBusiUtil.formatAmountToInt(rewardAmount));
				}
				Double balance = 0.0;
				Integer balanceInt = 0;
				String userName = "";
				if(accountSize > 0){
					for(Map account : allAcount){
						String accountUserCode = (String)account.get("USERCODE");
						if(userCode.equals(accountUserCode)){
							if(CommonUtil.isNotEmpty(account.get("USERNAME"))){
								userName = (String)account.get("USERNAME");
							}
							if(fundingType == CommonConstants.FUNDINGTYPE_PRESIENT_FLAG){
								balance = CommonUtil.formatDouble(account.get("PRESENTAMOUNT")).doubleValue();
								task.setBalanceText(CommonBusiUtil.formatAmount(balance));
							}else if(fundingType == CommonConstants.FUNDINGTYPE_SCORE_FLAG){
								balanceInt = CommonUtil.formatDouble(account.get("SCORE")).intValue();
								task.setBalanceText(balanceInt + "");
							}else if(fundingType == CommonConstants.FUNDINGTYPE_GOLD_FLAG){
								balanceInt = CommonUtil.formatDouble(account.get("GOLD")).intValue();
								task.setBalanceText(balanceInt + "");
							}
							break;
						}
					}
				}
				task.setUserName(userName);
				task.setBalance(balance);
				task.setFundingType((fundingType));
				taskVOList.add(task);
				
			}
		}
		j.setRows(taskVOList);
		return j;
	}
	@Override
	public TaskInfo getTask(String taskId) {
		String hql = "from TaskInfo t where t.taskId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		List<TaskInfo> taskList = taskDao.find(hql, param);
		if(taskList != null && taskList.size() > 0){
			return taskList.get(0);
		}
		return null;
	}

	public static void main(String[] args){
		StringBuilder sb = new StringBuilder("select t.task_id as taskId,t.name as name,t.describe as describe,");
		sb.append("t.datelimit_desc as dateLimitDesc,t.start_time as startTime,t.end_time as endTime,t.type as type,t.status as status,");
		sb.append("t.task_order as taskOrder,t.display_condition as displayCondition,t.receive_times as receiveTimes,");
		sb.append("t.is_progress_display as isProgressDisplay,t.has_prefix_condition as hasPrefixCondition,pre.type as prefixType,pre.times as prefixTimes,");
		sb.append("pre.prefix_task_id as prefixTaskId,pre.amount as prefixAmount,pre.first_amount as prefixFirstAmount,");
		sb.append("pre.total_amount as prefixTotalAmount,pos.type as postType,pos.times as postTimes,pos.amount as postAmount,");
		sb.append("pos.first_amount as postFirstAmount,pos.total_amount as postTotalAmount,pos.complete_flag as postCompleteFlag,");
		sb.append("r.type as rewardType,r.amount as rewardAmount,r.out_user_code as outUserCode,r.funding_type as fundingType,r.describe as rewardDescribe ");
		sb.append("from task_info t left join task_prefix_condition pre on t.task_id=pre.task_id left join task_post_condition pos ");
		sb.append("on t.task_id=pos.task_id left join task_result_reward r on t.task_id=r.task_id");
		System.out.println(sb.toString());
	}
	@Override
	public List<Map> queryInternalAcount() {
		String sql = "select a.user_code as userCode,a.recharge_amount as rechargeAmount,a.present_amount as presentAmount,a.bonus_amount as bonusAmount,a.score as score,a.gold as gold,u.user_name as userName from account a,user_member u where u.member_type=1 and u.user_code=a.user_code";
		List<Map> resList = accountDao.findBySql(sql);
		return resList;
	}
	@Override
	public void updateTask(TaskInfo task) {
		taskDao.update(task);
	}
	@Override
	public List<TaskInfo> getTaskInfoList(List<String> taskIdList) {
		if(taskIdList != null && taskIdList.size() > 0){
			StringBuilder sb = new StringBuilder("from TaskInfo t ");
			sb.append("where t.taskId in (");
			for(int i=0;i<taskIdList.size();i++){
				if(i == taskIdList.size() - 1){
					sb.append("?");
				}else{
					sb.append("?,");
				}
			}
			sb.append(")");
			List<TaskInfo> taskList = taskDao.find(sb.toString(), taskIdList.toArray());
			return taskList;
		}
		return null;
	}
	@Override
	public void deleteTaskList(List<TaskInfo> taskList) {
		for(TaskInfo task : taskList){
			String taskId = task.getTaskId();
			taskDao.delete(task);
			deleteTaskPrefix(taskId);
			deleteTaskPost(taskId);
			deleteTaskReward(taskId);
		}
		
	}
	@Override
	public TaskPost getTaskPost(String taskId) {
		String hql = "from TaskPost t where t.taskId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		List<TaskPost> taskPostList = taskPostDao.find(hql, param);
		if(taskPostList != null && taskPostList.size() > 0){
			return taskPostList.get(0);
		}
		return null;
	}
	@Override
	public TaskPrefix getTaskPrefix(String taskId) {
		String hql = "from TaskPrefix t where t.taskId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		List<TaskPrefix> taskPrefixList = taskPrefixDao.find(hql, param);
		if(taskPrefixList != null && taskPrefixList.size() > 0){
			return taskPrefixList.get(0);
		}
		return null;
	}
	@Override
	public TaskResultReward getTaskResultReward(String taskId) {
		String hql = "from TaskResultReward t where t.taskId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		List<TaskResultReward> taskResultRewardList = taskResultRewardDao.find(hql, param);
		if(taskResultRewardList != null && taskResultRewardList.size() > 0){
			return taskResultRewardList.get(0);
		}
		return null;
	}
	@Override
	public Map getTaskDetail(String taskId) {
		StringBuilder sb = new StringBuilder("select t.task_id as taskId,t.name as name,t.describe as describe,t.reward_describe as taskRewardDescribe,t.task_detail as taskDetail,");
		sb.append("t.datelimit_desc as dateLimitDesc,t.start_time as startTime,t.end_time as endTime,t.type as type,t.status as status,");
		sb.append("t.task_order as taskOrder,t.display_condition as displayCondition,t.receive_times as receiveTimes,");
		sb.append("t.is_progress_display as isProgressDisplay,t.has_prefix_condition as hasPrefixCondition,pre.type as prefixType,pre.times as prefixTimes,");
		sb.append("pre.prefix_task_id as prefixTaskId,pre.amount as prefixAmount,pre.first_amount as prefixFirstAmount,pre.lottery_type as prefixLotteryType,pre.lottery_type_name as prefixLotteryTypeName,");
		sb.append("pre.total_amount as prefixTotalAmount,pos.type as postType,pos.times as postTimes,pos.amount as postAmount,pos.lottery_type_name as postLotteryTypeName,");
		sb.append("pos.first_amount as postFirstAmount,pos.total_amount as postTotalAmount,pos.complete_flag as postCompleteFlag,pos.lottery_type as postLotteryType,pos.buy_type as postBuyType,");
		sb.append("r.type as rewardType,r.amount as rewardAmount,r.out_user_code as outUserCode,r.funding_type as fundingType,r.describe as rewardDescribe,r.account_name as accountName ");
		sb.append("from task_info t left join task_prefix_condition pre on t.task_id=pre.task_id left join task_post_condition pos ");
		sb.append("on t.task_id=pos.task_id left join task_result_reward r on t.task_id=r.task_id where t.task_id=?");
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		List<Map> resList = taskMapDao.findBySql(sb.toString(), param);
		if(resList != null && resList.size() > 0){
			return resList.get(0);
		}
		return null;
	}
	@Override
	public void updateTaskList(List<TaskInfo> taskList) {
		for(TaskInfo task : taskList){
			taskDao.update(task);
		}
		
	}
	@Override
	public void deleteTaskPost(String taskId) {
		String sql = "delete task_post_condition pos where pos.task_id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		taskPostDao.executeSql(sql, param);
	}
	@Override
	public void deleteTaskPrefix(String taskId) {
		String sql = "delete task_prefix_condition pre where pre.task_id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		taskPrefixDao.executeSql(sql, param);
		
	}
	@Override
	public void deleteTaskReward(String taskId) {
		String sql = "delete task_result_reward rew where rew.task_id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		taskResultRewardDao.executeSql(sql, param);
		
	}

	@Override
	public List<LotteryControl> getLotteryList(){
		String sql = "from LotteryControl order by sort asc ";
        return lotteryDao.find(sql, new Object[]{});
	}
	@Override
	public List<TaskPost> getTaskPostList(String taskId) {
		String hql = "from TaskPost t where t.taskId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		List<TaskPost> taskPostList = taskPostDao.find(hql, param);
		return taskPostList;
	}
	@Override
	public List<TaskPrefix> getTaskPrefixList(String taskId) {
		String hql = "from TaskPrefix t where t.taskId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		List<TaskPrefix> taskPrefixList = taskPrefixDao.find(hql, param);
		return taskPrefixList;
	}
	@Override
	public List<TaskResultReward> getTaskResultRewardList(String taskId) {
		String hql = "from TaskResultReward t where t.taskId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskId);
		List<TaskResultReward> taskResultRewardList = taskResultRewardDao.find(hql, param);
		return taskResultRewardList;
	}
	@Override
	public List<Cooperation> getCooperationList() {
		String hql = "from Cooperation order by sid asc";
		return cooperationDao.find(hql, new Object[]{});
	}
	/***
	 * 统计任务信息，用户数量/金额等
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map countTaskInfo(String taskId, Integer funddingType) {
		StringBuilder sb = new StringBuilder("select sum(case when t.status=1 then r.amount else 0 end) as notreward,");
		sb.append("sum(case when t.status=2 then r.amount else 0 end) as hasreward,");
		sb.append("sum(case when t.status=0 then 1 else 0 end) as doingnum,");
		sb.append("sum(case when t.status=1 then 1 else 0 end) as notrewardnum,");
		sb.append("sum(case when t.status=2 then 1 else 0 end) as hasrewardnum");
		sb.append(" from task_user_completion t ,task_result_reward r where t.task_id=r.task_id");
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.isNotEmpty(taskId)){
			sb.append(" and t.task_id = ? ");
			param.add(taskId);
		}
		if(CommonUtil.isNotEmpty(funddingType)){
			sb.append(" and r.funding_type = ? ");
			param.add(funddingType);
		}
		List<Map> resultList = taskMapDao.findBySql(sb.toString(), param);
		if(resultList != null && resultList.size() > 0){
			Map map = resultList.get(0);
			if(CommonConstants.FUNDINGTYPE_PRESIENT_FLAG == funddingType){
				if(CommonUtil.formatDouble(map.get("NOTREWARD")) == 0.0){
					map.put("NOTREWARD", "0.0");
				}else{
					map.put("NOTREWARD",CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("NOTREWARD"))));
				}
				if(CommonUtil.formatDouble(map.get("HASREWARD")) == 0.0){
					map.put("HASREWARD", "0.0");
				}else{
					map.put("HASREWARD",CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("HASREWARD"))));
				}
			}else{
				map.put("NOTREWARD", CommonUtil.formatDouble(map.get("NOTREWARD")).intValue());
				map.put("HASREWARD", CommonUtil.formatDouble(map.get("HASREWARD")).intValue());
			}
			int doingNum = CommonUtil.formatDouble(map.get("DOINGNUM")).intValue(); 
			int notRewardNum = CommonUtil.formatDouble(map.get("NOTREWARDNUM")).intValue(); 
			int hasRewardNum = CommonUtil.formatDouble(map.get("HASREWARDNUM")).intValue(); 
			int totalNum = doingNum + notRewardNum + hasRewardNum;
			map.put("TOTALNUM", totalNum);
			map.put("DOINGNUM", doingNum);
			map.put("NOTREWARDNUM", notRewardNum);
			map.put("HASREWARDNUM", hasRewardNum);
			return map;
		}
		return null;
	}
	
}
