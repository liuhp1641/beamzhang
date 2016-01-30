package com.cm.manage.service.operate.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.dao.operate.ITaskDao;
import com.cm.manage.model.member.Cooperation;
import com.cm.manage.model.operate.TaskInfo;
import com.cm.manage.model.operate.TaskPost;
import com.cm.manage.model.operate.TaskPrefix;
import com.cm.manage.model.operate.TaskResultReward;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.ITaskService;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.TaskVO;
@Service("taskService")
public class TaskServiceImpl extends BaseServiceImpl implements
		ITaskService {
	private static Logger logger = Logger.getLogger(TaskServiceImpl.class);

	@Autowired
	private ITaskDao taskInfoDao;

	@Override
	public void saveTaskInfo(TaskVO taskVO) throws BusiException, IllegalAccessException, InvocationTargetException {
		TaskInfo task = new TaskInfo();
		TaskPrefix prefix = new TaskPrefix();
		TaskPost post = new TaskPost();
		TaskResultReward reward = new TaskResultReward();
		String taskId = CommonBusiUtil.getTaskId();
		Date date = new Date();
		String dateStr = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
		Date createTime = DateUtil.format(dateStr);
		task.setTaskId(taskId);
		task.setCreateTime(createTime);
		prefix.setTaskId(taskId);
		prefix.setCreateTime(createTime);
		post.setTaskId(taskId);
		post.setCreateTime(createTime);
		reward.setTaskId(taskId);
		reward.setCreateTime(createTime);
		task.setName(taskVO.getName());
		task.setDescribe(taskVO.getDescribe());
		task.setTaskDetail(taskVO.getTaskDetail());
		task.setRewardDescribe(taskVO.getTaskRewardDescribe());
		task.setRewardTarget(taskVO.getRewardTarget());
		if(CommonUtil.isNotEmpty(taskVO.getStartTime())){
			task.setStartTime(DateUtil.format(taskVO.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		}else{
			String str = DateUtil.format(new Date());
			task.setStartTime(DateUtil.format(str, "yyyy-MM-dd HH:mm:ss"));
		}
		if(CommonUtil.isNotEmpty(taskVO.getEndTime())){
			task.setEndTime(DateUtil.format(taskVO.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		}
		//暂时任务都设置为条件型
		task.setType(CommonConstants.TASK_TYPE_CONDITION_FLAG);
		task.setStatus(CommonConstants.TASK_STATUS_NEW);//默认未发布
		if(CommonUtil.isNotEmpty(taskVO.getTaskOrder())){
			task.setTaskOrder(taskVO.getTaskOrder());
		}else{
			task.setTaskOrder(CommonConstants.TASK_ORDER_DEFAULT_FLAG);
		}
		task.setDateLimitDesc(taskVO.getDateLimitDesc());
		if(CommonConstants.TASK_SHOW_AND_DO == taskVO.getDisplayCondition()){
			task.setDisplayCondition(CommonConstants.TASK_SHOW_AND_DO);
			task.setHasPrefixCondition(CommonConstants.TASK_HAS_NO_PREFIX_FLAG);
		}else{
			task.setDisplayCondition(taskVO.getDisplayCondition());
			task.setHasPrefixCondition(CommonConstants.TASK_HAS_PREFIX_FLAG);
		}
		if(CommonUtil.isNotEmpty(taskVO.getReceiveTimes())){
			task.setReceiveTimes(taskVO.getReceiveTimes());
		}else{
			task.setReceiveTimes(CommonConstants.TASK_RECEIVETIMES_DEFAULT_FLAG);
		}
		
		if( CommonUtil.isNotEmpty(taskVO.getIsProgressDisplay())){
			task.setIsProgressDisplay(taskVO.getIsProgressDisplay());
		}else{
			task.setIsProgressDisplay(CommonConstants.TASK_PROGRESS_HIDE_FLAG);
		}
		
		//前置条件
		if(CommonConstants.TASK_SHOW_AND_DO == taskVO.getDisplayCondition()){
			prefix.setPrefixTaskId(CommonConstants.PREFIX_TASK_NOT_EXSIT);
			prefix.setPrefixTaskName("");
			prefix.setAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
			prefix.setTimes(CommonConstants.TASK_TIMES_NOT_LIMIT_FLAG);
			prefix.setTotalAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
			prefix.setFirstAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
			prefix.setType(CommonConstants.TASK_CONDITION_NOT_EXSIT);
			prefix.setLotteryType(CommonConstants.LOTTERY_NO_LIMIT_FLAG);
			prefix.setLotteryTypeName("");
			prefix.setListType(CommonConstants.PREFIX_TASK_INCLUSION_FLAG);
			prefix.setVipRank(CommonConstants.USER_VIP_RANK_ZERO);
			prefix.setSid(CommonConstants.CHANNEL_NO_LIMIT_FLAG);
			prefix.setSidName("");
		}else{
			if(CommonUtil.isNotEmpty(taskVO.getPrefixType())){
				prefix.setType(taskVO.getPrefixType());
			}else{
				prefix.setType(CommonConstants.TASK_CONDITION_NOT_EXSIT);
			}
			if(CommonUtil.isNotEmpty(taskVO.getPrefixTimes())){
				prefix.setTimes(taskVO.getPrefixTimes());
			}else{
				prefix.setTimes(CommonConstants.TASK_TIMES_NOT_LIMIT_FLAG);
			}
			if(CommonUtil.isNotEmpty(taskVO.getPrefixAmount())){
				prefix.setAmount(taskVO.getPrefixAmount());
			}else{
				prefix.setAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
			}
			if(CommonUtil.isNotEmpty(taskVO.getPrefixFirstAmount())){
				prefix.setFirstAmount(taskVO.getPrefixFirstAmount());
			}else{
				prefix.setFirstAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
			}
			if(CommonUtil.isNotEmpty(taskVO.getPrefixTotalAmount())){
				prefix.setTotalAmount(taskVO.getPrefixTotalAmount());
			}else{
				prefix.setTotalAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
			}
			if(CommonUtil.isNotEmpty(taskVO.getPrefixTaskId()) && ! CommonConstants.PREFIX_TASK_NOT_EXSIT.equals(taskVO.getPrefixTaskId())){
				//校验前置任务ID是否有效
				String prefixTaskId = CommonUtil.trim(taskVO.getPrefixTaskId());
				TaskInfo prefixTask = taskInfoDao.getTask(prefixTaskId);
				if(prefixTask == null){
					throw new BusiException("","新建任务失败,前置任务不存在");
				}
				prefix.setPrefixTaskId(prefixTaskId);
			}else{
				prefix.setPrefixTaskId(CommonConstants.PREFIX_TASK_NOT_EXSIT);
			}
			prefix.setLotteryType(taskVO.getPostLotteryType());
			prefix.setLotteryTypeName(taskVO.getPrefixLotteryTypeName());
			prefix.setListType(taskVO.getPrefixListType());
			prefix.setVipRank(taskVO.getPrefixVipRank());
			prefix.setSid(taskVO.getPrefixSid());
			prefix.setSidName(taskVO.getPrefixSidName());
			prefix.setPrefixTaskName(taskVO.getPrefixTaskName());
		}
		
		int completeNum = 0;//完成条件数量
		//后置条件
		if(CommonUtil.isNotEmpty(taskVO.getPostType())){
			post.setType(taskVO.getPostType());
		}else{
			post.setType(CommonConstants.TASK_CONDITION_NOT_EXSIT);
		}
		if(CommonUtil.isNotEmpty(taskVO.getPostTimes())){
			post.setTimes(taskVO.getPostTimes());
		}else{
			post.setTimes(CommonConstants.TASK_TIMES_NOT_LIMIT_FLAG);
		}
		if(CommonUtil.isNotEmpty(taskVO.getPostAmount())){
			post.setAmount(taskVO.getPostAmount());
		}else{
			post.setAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
		}
		if(CommonUtil.isNotEmpty(taskVO.getPostFirstAmount())){
			post.setFirstAmount(taskVO.getPostFirstAmount());
		}else{
			post.setFirstAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
		}
		if(CommonUtil.isNotEmpty(taskVO.getPostTotalAmount())){
			post.setTotalAmount(taskVO.getPostTotalAmount());
		}else{
			post.setTotalAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
		}		
		if(CommonUtil.isNotEmpty(taskVO.getPostLotteryType())){
			post.setLotteryType(taskVO.getPostLotteryType());
		}else{
			post.setLotteryType(CommonConstants.LOTTERY_NO_LIMIT_FLAG);
		}
		post.setLotteryTypeName(taskVO.getPostLotteryTypeName());
		if(CommonUtil.isNotEmpty(taskVO.getPostBuyType())){
			post.setBuyType(taskVO.getPostBuyType());
		}else{
			post.setBuyType(CommonConstants.ORDER_BYETYPE_ALL);
		}
		if(CommonUtil.isNotEmpty(taskVO.getPostAmountHigh())){
			post.setAmountHigh(taskVO.getPostAmountHigh());
		}else{
			post.setAmountHigh(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
		}
		if(CommonUtil.isNotEmpty(taskVO.getPostFirstAmountHigh())){
			post.setFirstAmountHigh(taskVO.getPostFirstAmountHigh());
		}else{
			post.setFirstAmountHigh(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
		}
		if(CommonUtil.isNotEmpty(taskVO.getPostFirstNoLottery())){
			post.setFirstNoLottery(taskVO.getPostFirstNoLottery());
		}else{
			post.setFirstNoLottery(CommonConstants.FIRST_NO_LOTTERY_NO_FLAG);
		}
		
		
		
		//奖励结果
		if(CommonUtil.isNotEmpty(taskVO.getOutUserCode())){
			reward.setOutUserCode(taskVO.getOutUserCode());
		}else{
			throw new BusiException("","账户编号丢失");
		}
		if(CommonUtil.isNotEmpty(taskVO.getRewardAmount())){
			reward.setAmount(taskVO.getRewardAmount());
		}else{
			reward.setAmount(CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG);
		}
		reward.setDescribe(taskVO.getRewardDescribe());
		
		int fundingType = CommonConstants.FUNDINGTYPE_PRESIENT_FLAG;//默认红包
		if(CommonUtil.isNotEmpty(taskVO.getFundingType())){
			fundingType = taskVO.getFundingType();
		}
		reward.setFundingType(fundingType);
		if(fundingType == CommonConstants.FUNDINGTYPE_PRESIENT_FLAG || 
				fundingType == CommonConstants.FUNDINGTYPE_SCORE_FLAG || 
				fundingType == CommonConstants.FUNDINGTYPE_GOLD_FLAG){
			reward.setType(CommonConstants.TASK_REWARD_FUNDING_FLAG);//资金奖励
			reward.setDescribe("该任务完成将给以资金型的奖励");
		}else{
			reward.setType(CommonConstants.TASK_REWARD_HONOR_FLAG);//其他型如头衔型
			reward.setDescribe("该任务完成将给以虚拟型的奖励，如头衔、VIP称号等");
		}
		reward.setAccountName(taskVO.getAccountName());
		if(CommonUtil.isNotEmpty(taskVO.getRewardVipRank())){
			reward.setVipRank(taskVO.getRewardVipRank());
		}else{
			reward.setVipRank(CommonConstants.USER_VIP_RANK_ZERO);
		}
		
		
		
		
		
		//注册类型
		if(CommonConstants.TASK_CONDITION_PERFECTINFO_FLAG == taskVO.getPostType()){
			post.setVipRank(CommonConstants.USER_VIP_RANK_ZERO);
			 if(CommonConstants.VALID_YES_FLAG == taskVO.getPhoneNoMust()){
				 completeNum ++;
				 post.setCompleteFlag(CommonConstants.COMPLETE_FLAG_PHONE);//手机号需验证
				 taskInfoDao.saveTaskPost(post);
			 }
			 if(CommonConstants.VALID_YES_FLAG == taskVO.getNameMust()){//姓名
				 completeNum ++;
				 TaskPost post2 = new TaskPost();
				 BeanUtils.copyProperties(post2, post);
				 post2.setCompleteFlag(CommonConstants.COMPLETE_FLAG_NAME);
				 taskInfoDao.saveTaskPost(post2);
			 }
			 if(CommonConstants.VALID_YES_FLAG == taskVO.getCardNoMust()){//身份证
				 completeNum ++;
				 TaskPost post3 = new TaskPost();
				 BeanUtils.copyProperties(post3, post);
				 post3.setCompleteFlag(CommonConstants.COMPLETE_FLAG_IDCARD);
				 taskInfoDao.saveTaskPost(post3);
			 }
			 if(CommonConstants.VALID_YES_FLAG == taskVO.getVipRankMust()){//vip
				 completeNum ++;
				 TaskPost post4 = new TaskPost();
				 BeanUtils.copyProperties(post4, post);
				 post4.setVipRank(taskVO.getPostVipRank());
				 post4.setCompleteFlag(CommonConstants.COMPLETE_FLAG_VIP);
				 taskInfoDao.saveTaskPost(post4);
			 }
		}else{
			completeNum = 1;
			post.setCompleteFlag(CommonConstants.COMPLETE_FLAG_NO);
			post.setVipRank(CommonConstants.USER_VIP_RANK_ZERO);
			taskInfoDao.saveTaskPost(post);
		}
		taskInfoDao.saveTaskReward(reward);
		task.setCompleteNum(completeNum);
		taskInfoDao.saveTask(task);
		taskInfoDao.saveTaskPrefix(prefix);
		
	}

	@Override
	public EasyuiDataGridJson queryTaskList(EasyuiDataGrid dg, TaskVO taskVO){
		return taskInfoDao.queryTaskList(dg, taskVO);
		
	}

	@Override
	public List<Map> queryInternalAcount() {
		return taskInfoDao.queryInternalAcount();
	}

	@Override
	public Map<String,String> updateTaskStatus(String taskId, int status,String operate) throws BusiException {
		if(status != CommonConstants.TASK_STATUS_NEW && status != CommonConstants.TASK_STATUS_START 
				&& status != CommonConstants.TASK_STATUS_STOP && status != CommonConstants.TASK_STATUS_END){
			throw new BusiException("","任务状态参数传递错误");
		}
		TaskInfo task = taskInfoDao.getTask(taskId);
		if(task == null){
			throw new BusiException("","该任务已经不存在");
		}
		//如果是暂停任务，任务一定要是已上线运行的
		if(CommonConstants.TASK_OPERATE_STOP.equals(operate)){
			if(CommonConstants.TASK_STATUS_START != task.getStatus()){
				throw new BusiException("","该任务不能进行暂停,请确认后再操作");
			}
		//恢复任务	
		}else if(CommonConstants.TASK_OPERATE_RECOVER.equals(operate)){
			if(CommonConstants.TASK_STATUS_STOP != task.getStatus()){
				throw new BusiException("","该任务不能进行恢复,请确认后再操作");
			}
			Date endTime = task.getEndTime();
			if(endTime != null){
				Date now = new Date();
				if(now.after(endTime)){
					throw new BusiException("","该任务已经过期不能恢复");
				}
			}
			
			
		//结束任务，不能结束未发布的任务，未发布任务可以删除
		}else if(CommonConstants.TASK_OPERATE_END.equals(operate)){
			if(CommonConstants.TASK_STATUS_NEW == task.getStatus()){
				throw new BusiException("","该任务还未发布,不能进行结束操作");
			}
		}else{
			throw new BusiException("","操作参数丢失");
		}
		task.setStatus(status);
		taskInfoDao.updateTask(task);
		//准备日志数据
		Map<String,String> logDataMap = new HashMap<String,String>();
		logDataMap.put("taskName", task.getName());
		return logDataMap;
	}

	@Override
	public Map<String,String> deleteTask(String taskIdStr) throws BusiException {
		StringBuilder taskNames = new StringBuilder();
		if(taskIdStr == null || "".equals(taskIdStr)){
			throw new BusiException("","任务ID丢失");
    	}
    	String[] taskIdArray = taskIdStr.split("\\|");
    	if(taskIdArray == null || taskIdArray.length == 0){
    		throw new BusiException("","任务ID丢失");
    	}
    	if(taskIdArray.length > 50){
    		throw new BusiException("","批处理数据过多，最多同时操作50条记录");
    	}
    	List<String> taskIdList = Arrays.asList(taskIdArray);
    	List<TaskInfo> taskList = taskInfoDao.getTaskInfoList(taskIdList);
    	if(taskList != null && taskList.size() > 0){
    		//开始过滤不可进行操作的任务，只有状态为0即还未发布的任务可以删除
    		Iterator it = taskList.iterator();
    		while(it.hasNext()){
    			TaskInfo task = (TaskInfo)it.next();
    			if(task == null || CommonConstants.TASK_STATUS_NEW != task.getStatus()){
    				it.remove();
    				continue;
    			}
    			taskNames.append(task.getName() + "|");
    		}
    		taskInfoDao.deleteTaskList(taskList);    		
    	}
    	//准备日志数据
    	Map<String,String> logDataMap = new HashMap<String,String>();
		logDataMap.put("taskName", taskNames.toString());
		return logDataMap;
	}

	@Override
	public TaskVO getTaskDetailInfo(String taskId) {
		TaskVO task = new TaskVO();
		try{
			List<Map> allAccount = queryInternalAcount();
			Map map = taskInfoDao.getTaskDetail(taskId);
			if(map != null){
				task.setName((String)map.get("NAME"));
				task.setTaskId((String)map.get("TASKID"));
				task.setDateLimitDesc((String)map.get("DATELIMITDESC"));
				task.setDescribe((String)map.get("DESCRIBE"));
				task.setTaskDetail((String)map.get("TASKDETAIL"));
				task.setTaskRewardDescribe((String)map.get("TASKREWARDDESCRIBE"));
				Date startTime = (Date) map.get("STARTTIME");
				task.setStartTime(DateUtil.format(startTime));
				if(map.get("ENDTIME") != null){
					Date endTime = (Date) map.get("ENDTIME");
					task.setEndTime(DateUtil.format(endTime));
				}
				task.setStatus(CommonUtil.formatDouble(map.get("STATUS")).intValue());
				task.setType(CommonUtil.formatDouble(map.get("TYPE")).intValue());
				task.setTaskOrder(CommonUtil.formatDouble(map.get("TASKORDER")).intValue());
				task.setDisplayCondition(CommonUtil.formatDouble(map.get("DISPLAYCONDITION")).intValue());
				task.setReceiveTimes(CommonUtil.formatDouble(map.get("RECEIVETIMES")).intValue());
				task.setIsProgressDisplay(CommonUtil.formatDouble(map.get("ISPROGRESSDISPLAY")).intValue());
				task.setHasPrefixCondition(CommonUtil.formatDouble(map.get("HASPREFIXCONDITION")).intValue());
				
				task.setPrefixTaskId((String)map.get("PREFIXTASKID"));
				task.setPrefixType(CommonUtil.formatDouble(map.get("PREFIXTYPE")).intValue());
				task.setPrefixLotteryType((String)map.get("PREFIXLOTTERYTYPE"));
				task.setPrefixLotteryTypeName((String)map.get("PREFIXLOTTERYTYPENAME"));
				task.setPrefixTimes(CommonUtil.formatDouble(map.get("PREFIXTIMES")).intValue());
				task.setPrefixFirstAmount(CommonUtil.formatDouble(map.get("PREFIXFIRSTAMOUNT")).doubleValue());
				task.setPrefixAmount(CommonUtil.formatDouble(map.get("PREFIXAMOUNT")).doubleValue());
				task.setPrefixTotalAmount(CommonUtil.formatDouble(map.get("PREFIXTOTALAMOUNT")).doubleValue());
				
				task.setPostType(CommonUtil.formatDouble(map.get("POSTTYPE")).intValue());
				task.setPostLotteryType((String)map.get("POSTLOTTERYTYPE"));
				task.setPostLotteryTypeName((String)map.get("POSTLOTTERYTYPENAME"));
				task.setPostTimes(CommonUtil.formatDouble(map.get("POSTTIMES")).intValue());
				task.setPostAmount(CommonUtil.formatDouble(map.get("POSTAMOUNT")).doubleValue());
				task.setPostFirstAmount(CommonUtil.formatDouble(map.get("POSTFIRSTAMOUNT")).doubleValue());
				task.setPostTotalAmount(CommonUtil.formatDouble(map.get("POSTTOTALAMOUNT")).doubleValue());
				task.setPostCompleteFlag(CommonUtil.formatDouble(map.get("POSTCOMPLETEFLAG")).intValue());
				task.setPostType(CommonUtil.formatDouble(map.get("POSTBUYTYPE")).intValue());
				
				task.setRewardAmount(CommonUtil.formatDouble(map.get("REWARDAMOUNT")).doubleValue());
				int fundingType = CommonUtil.formatDouble(map.get("FUNDINGTYPE")).intValue();
				task.setFundingType(fundingType);
				String userCode = (String)map.get("OUTUSERCODE");
				task.setOutUserCode(userCode);
				task.setRewardDescribe((String)map.get("REWARDDESCRIBE"));
				task.setRewardType(CommonUtil.formatDouble(map.get("REWARDTYPE")).intValue());
				
				int completeFlag = 0;
				if(CommonUtil.isNotEmpty(map.get("POSTCOMPLETEFLAG"))){
					completeFlag = CommonUtil.formatDouble(map.get("POSTCOMPLETEFLAG")).intValue();
				}
				task.setPostCompleteFlag(completeFlag);
				String userName = "";
				Double balance = CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG;
				if(allAccount != null && allAccount.size() > 0){
					for(Map account : allAccount){
						String accountUserCode = (String)account.get("USERCODE");
						if(userCode.equals(accountUserCode)){
							if(CommonUtil.isNotEmpty(account.get("USERNAME"))){
								userName = (String)account.get("USERNAME");
							}
							if(fundingType == CommonConstants.FUNDINGTYPE_PRESIENT_FLAG){
								balance = CommonUtil.formatDouble(account.get("PRESENTAMOUNT")).doubleValue();
							}else if(fundingType == CommonConstants.FUNDINGTYPE_SCORE_FLAG){
								balance = CommonUtil.formatDouble(account.get("SCORE")).doubleValue();
							}else if(fundingType == CommonConstants.FUNDINGTYPE_GOLD_FLAG){
								balance = CommonUtil.formatDouble(account.get("GOLD")).doubleValue();
							}
							break;
						}
					}
				}
				task.setBalanceText(CommonBusiUtil.formatAmount(balance));
				task.setUserName(userName);
				task.setBalance(balance);
			}
		}catch(Exception e){
			logger.error(e);
		}
		return task;
	}

	@Override
	public Map<String,String> startTask(String taskIdStr) throws BusiException {
		StringBuilder taskNames = new StringBuilder();
		if(taskIdStr == null || "".equals(taskIdStr)){
			throw new BusiException("","任务ID丢失");
    	}
    	String[] taskIdArray = taskIdStr.split("\\|");
    	if(taskIdArray == null || taskIdArray.length == 0){
    		throw new BusiException("","任务ID丢失");
    	}
    	if(taskIdArray.length > 50){
    		throw new BusiException("","批处理数据过多，最多同时操作50条记录");
    	}
    	List<String> taskIdList = Arrays.asList(taskIdArray);
    	List<TaskInfo> taskList = taskInfoDao.getTaskInfoList(taskIdList);
    	if(taskList != null && taskList.size() > 0){
    		//开始过滤不可进行操作的任务，只有状态为0即还未发布的任务可以删除
    		Iterator it = taskList.iterator();
    		while(it.hasNext()){
    			TaskInfo task = (TaskInfo)it.next();
    			if(task == null || CommonConstants.TASK_STATUS_NEW != task.getStatus()){
    				it.remove();
    				continue;
    			}
    			taskNames.append(task.getName() + "|");
    			task.setStatus(CommonConstants.TASK_STATUS_START);//任务启动
    			task.setStartTime(new Date());
    		}
    		taskInfoDao.updateTaskList(taskList);    		
    	}
    	//准备日志数据
    	Map<String,String> logDataMap = new HashMap<String,String>();
		logDataMap.put("taskName", taskNames.toString());
		return logDataMap;
		
	}

	@Override
	public List<LotteryControl> getLotteryList() {
		return taskInfoDao.getLotteryList();
	}

	@Override
	public TaskInfo getTask(String taskId) {
		return taskInfoDao.getTask(taskId);
	}

	@Override
	public Map<String,Object> getTaskDetail(String taskId) {
		TaskInfo taskInfo = taskInfoDao.getTask(taskId);
		List<TaskPrefix> TaskPrefixList = taskInfoDao.getTaskPrefixList(taskId);
		List<TaskPost> TaskPostList = taskInfoDao.getTaskPostList(taskId);
		List<TaskResultReward> taskRewardList = taskInfoDao.getTaskResultRewardList(taskId);
		List<Map> allAccount = queryInternalAcount();
		Map<String,Object> resMap = new HashMap<String,Object>();
		String startTime = DateUtil.format(taskInfo.getStartTime());
		String endTime = "";
		if(CommonUtil.isNotEmpty(taskInfo.getEndTime())){
			endTime = DateUtil.format(taskInfo.getEndTime());
		}
		resMap.put("startTime", startTime);
		resMap.put("endTime", endTime);
		resMap.put("taskInfo", taskInfo);
		if(TaskPrefixList != null && TaskPrefixList.size() > 0){
			resMap.put("taskPrefix", TaskPrefixList.get(0));
			resMap.put("taskPrefixType", TaskPrefixList.get(0).getType());
		}
		if(TaskPostList != null && TaskPostList.size() > 0){
			resMap.put("taskPostType", TaskPostList.get(0).getType());
		}
		resMap.put("taskPostList", TaskPostList);
		String userCode = "";
		int fundingType = CommonConstants.FUNDINGTYPE_PRESIENT_FLAG;
		String rewardAmount = "";
		if(taskRewardList != null && taskRewardList.size() > 0){
			resMap.put("taskReward", taskRewardList.get(0));
			userCode = taskRewardList.get(0).getOutUserCode();
			fundingType = taskRewardList.get(0).getFundingType();
			if(CommonConstants.FUNDINGTYPE_PRESIENT_FLAG != fundingType){
				rewardAmount = CommonBusiUtil.formatAmountToInt(taskRewardList.get(0).getAmount());
			}else{
				rewardAmount = CommonBusiUtil.formatAmount(taskRewardList.get(0).getAmount());
			}
			resMap.put("rewardAmount",rewardAmount);
		}
		String userName = "";
		Double balance = CommonConstants.TASK_AMOUNT_NOT_LIMIT_FLAG;
		int balaceInt = 0;
		if(allAccount != null && allAccount.size() > 0){
			for(Map account : allAccount){
				String accountUserCode = (String)account.get("USERCODE");
				if(userCode.equals(accountUserCode)){
					if(CommonUtil.isNotEmpty(account.get("USERNAME"))){
						userName = (String)account.get("USERNAME");
					}
					if(fundingType == CommonConstants.FUNDINGTYPE_PRESIENT_FLAG){
						balance = CommonUtil.formatDouble(account.get("PRESENTAMOUNT")).doubleValue();
						resMap.put("balanceText",CommonBusiUtil.formatAmount(balance));
					}else if(fundingType == CommonConstants.FUNDINGTYPE_SCORE_FLAG){
						balaceInt = CommonUtil.formatDouble(account.get("SCORE")).intValue();
						resMap.put("balanceText",balaceInt);
					}else if(fundingType == CommonConstants.FUNDINGTYPE_GOLD_FLAG){
						balaceInt = CommonUtil.formatDouble(account.get("GOLD")).intValue();
						resMap.put("balanceText",balaceInt);
					}
					break;
				}
			}
		}
		resMap.put("outUserName", userName);
		
		//统计任务用户/金额相关信息
		Map userTaskInfoMap = taskInfoDao.countTaskInfo(taskId, fundingType);
		resMap.put("countUserTask", userTaskInfoMap);
		return resMap;
	}

	@Override
	public List<Cooperation> getOperationList() {
		return taskInfoDao.getCooperationList();
	}
	
	
}
