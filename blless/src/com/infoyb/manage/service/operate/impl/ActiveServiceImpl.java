package com.cm.manage.service.operate.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.dao.operate.IActiveDao;
import com.cm.manage.model.operate.TaskExchange;
import com.cm.manage.model.operate.TaskExchangeDetail;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IActiveService;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.ExchangeVO;
@Service("activeService")
public class ActiveServiceImpl extends BaseServiceImpl implements IActiveService {
	@Autowired
	private IActiveDao activeDao;

	@Override
	public void saveTaskExchange(ExchangeVO exchangeVO) {
		TaskExchange taskExchange = new TaskExchange();
		taskExchange.setExchangeId(CommonBusiUtil.getActiveId());
		taskExchange.setStatus(CommonConstants.ACTIVE_STATUS_NEW);
		taskExchange.setExchangeName(exchangeVO.getExchangeName());
		taskExchange.setExchangeNote(exchangeVO.getExchangeNote());
		taskExchange.setStartTime(DateUtil.format(exchangeVO.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		taskExchange.setEndTime(DateUtil.format(exchangeVO.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		taskExchange.setExchangeUserCode(exchangeVO.getExchangeUserCode());
		taskExchange.setExchangeUserName(exchangeVO.getExchangeUserName());
		taskExchange.setExchangeGroup(exchangeVO.getExchangeGroup());
		taskExchange.setIsBindingMobile(exchangeVO.getIsBindingMobile());
		int returnType = CommonConstants.ACTIVE_RETURNTYPE_NO;
		if(CommonUtil.isNotEmpty(exchangeVO.getReturnType())){
			returnType = exchangeVO.getReturnType();
		}
		taskExchange.setReturnType(returnType);
		if(returnType == CommonConstants.ACTIVE_RETURNTYPE_NO){
			taskExchange.setReturnNumbers(0);
			taskExchange.setEachNumber(0);
			taskExchange.setEachUnit(0);
		}else{
			taskExchange.setReturnNumbers(exchangeVO.getReturnNumbers());
			taskExchange.setEachNumber(exchangeVO.getEachNumber());
			taskExchange.setEachUnit(exchangeVO.getEachUnit());
		}
		taskExchange.setSid(exchangeVO.getSid());
		taskExchange.setSidName(exchangeVO.getSidName());
		Date date = new Date();
		taskExchange.setCreateTime(date);
		taskExchange.setUpdateTime(date);
		activeDao.saveTaskExchange(taskExchange);
		
	}

	@Override
	public void saveTaskExchangeDetail(TaskExchangeDetail taskExchangeDetail) {
		activeDao.saveTaskExchangeDetail(taskExchangeDetail);
		
	}

	@Override
	public List<TaskExchangeDetail> findExchangeDetailListById(
			String taskExchangeId) {
		return activeDao.findExchangeDetailListById(taskExchangeId);
	}

	@Override
	public TaskExchange findTaskExchangeById(String taskExchangeId) {
		return activeDao.findTaskExchangeById(taskExchangeId);
	}

	@Override
	public TaskExchangeDetail findTaskExchangeDetailById(
			String taskExchangeDetailId) {
		return activeDao.findTaskExchangeDetailById(taskExchangeDetailId);
	}

	@Override
	public void updateTaskExchange(TaskExchange taskExchange) {
		activeDao.updateTaskExchange(taskExchange);
		
	}

	@Override
	public void updateTaskExchangeDetail(TaskExchangeDetail detail) {
		activeDao.updateTaskExchangeDetail(detail);
		
	}

	@Override
	public void editTaskExchange(ExchangeVO exchangeVO) throws BusiException {
		String exchangeId = exchangeVO.getExchangeId();
		if(null == exchangeId || "" .equals(exchangeId)){
			throw new BusiException("","活动ID丢失");
		}
		TaskExchange taskExchange = activeDao.findTaskExchangeById(exchangeId);
		if(taskExchange == null){
			throw new BusiException("","查找活动失败");
		}
		if(taskExchange.getStatus() != CommonConstants.ACTIVE_STATUS_NEW){
			throw new BusiException("","当前活动不可进行编辑，请确认后操作");
		}
		taskExchange.setExchangeName(exchangeVO.getExchangeName());
		taskExchange.setExchangeNote(exchangeVO.getExchangeNote());
		taskExchange.setStartTime(DateUtil.format(exchangeVO.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		taskExchange.setEndTime(DateUtil.format(exchangeVO.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		taskExchange.setExchangeUserCode(exchangeVO.getExchangeUserCode());
		taskExchange.setExchangeUserName(exchangeVO.getExchangeUserName());
		int returnType = CommonConstants.ACTIVE_RETURNTYPE_NO;
		if(CommonUtil.isNotEmpty(exchangeVO.getReturnType())){
			returnType = exchangeVO.getReturnType();
		}
		taskExchange.setReturnType(returnType);
		if(returnType == CommonConstants.ACTIVE_RETURNTYPE_NO){
			taskExchange.setReturnNumbers(0);
			taskExchange.setEachNumber(0);
			taskExchange.setEachUnit(0);
		}else{
			taskExchange.setReturnNumbers(exchangeVO.getReturnNumbers());
			taskExchange.setEachNumber(exchangeVO.getEachNumber());
			taskExchange.setEachUnit(exchangeVO.getEachUnit());
		}
		taskExchange.setSid(exchangeVO.getSid());
		taskExchange.setSidName(exchangeVO.getSidName());
		Date date = new Date();
		taskExchange.setUpdateTime(date);
		activeDao.updateTaskExchange(taskExchange);
	}

	@Override
	public EasyuiDataGridJson activeList(EasyuiDataGrid dataGrid,
			ExchangeVO exchangeVO) {
		return activeDao.activeList(dataGrid, exchangeVO);
	}

	@Override
	public Map<String,String> deleteTaskExchange(String taskExchangeIdStr) throws BusiException {
		
		StringBuilder activeNames = new StringBuilder();
		if(taskExchangeIdStr == null || "".equals(taskExchangeIdStr)){
			throw new BusiException("","活动ID丢失");
    	}
    	String[] activeIdArray = taskExchangeIdStr.split("\\|");
    	if(activeIdArray == null || activeIdArray.length == 0){
    		throw new BusiException("","活动ID丢失");
    	}
    	if(activeIdArray.length > 100){
    		throw new BusiException("","批处理数据过多，最多同时操作100条记录");
    	}
    	List<String> activeIdList = Arrays.asList(activeIdArray);
    	List<TaskExchange> taskExchangeList = activeDao.getTaskExchangeList(activeIdList);
    	if(taskExchangeList != null && taskExchangeList.size() > 0){
    		Iterator it = taskExchangeList.iterator();
    		while(it.hasNext()){
    			TaskExchange task = (TaskExchange)it.next();
    			if(task == null || CommonConstants.ACTIVE_STATUS_NEW != task.getStatus()){
    				it.remove();
    				continue;
    			}
    			activeNames.append(task.getExchangeName() + "|");
    		}
    		activeDao.deleteTaskExchangeList(taskExchangeList);  		
    	}
    	//准备日志数据
    	Map<String,String> logDataMap = new HashMap<String,String>();
		logDataMap.put("activeName", activeNames.toString());
		return logDataMap;
		
		
	}
	@Override
	public Map<String,String> deleteTaskExchangeDetail(String taskExchangeDetailIdStr) throws BusiException {
		
		StringBuilder activeNames = new StringBuilder();
		if(taskExchangeDetailIdStr == null || "".equals(taskExchangeDetailIdStr)){
			throw new BusiException("","方案ID丢失");
    	}
    	String[] activeItemIdArray = taskExchangeDetailIdStr.split("\\|");
    	if(activeItemIdArray == null || activeItemIdArray.length == 0){
    		throw new BusiException("","方案ID丢失");
    	}
    	if(activeItemIdArray.length > 100){
    		throw new BusiException("","批处理数据过多，最多同时操作100条记录");
    	}
    	List<String> activeItemIdList = Arrays.asList(activeItemIdArray);
    	List<TaskExchangeDetail> taskExchangeDetailList = activeDao.getTaskExchangeDetailList(activeItemIdList);
    	if(taskExchangeDetailList != null && taskExchangeDetailList.size() > 0){
    		//只有状态为0即还未发布的方案可以删除
    		Iterator it = taskExchangeDetailList.iterator();
    		while(it.hasNext()){
    			TaskExchangeDetail task = (TaskExchangeDetail)it.next();
    			if(task == null || CommonConstants.ACTIVE_ITEM_STATUS_NEW != task.getStatus()){
    				it.remove();
    				continue;
    			}
    			activeNames.append(task.getExchangeDetailId() + "|");
    		}
    		activeDao.deleteTaskExchangeDetailList(taskExchangeDetailList);  		
    	}
    	//准备日志数据
    	Map<String,String> logDataMap = new HashMap<String,String>();
		logDataMap.put("activeName", activeNames.toString());
		return logDataMap;		
	}
	/***
	 * @describe 启动方案
	 * @param taskIdStr
	 * @return
	 * @throws BusiException
	 */
	@Override
	public Map<String,String> startActiveItem(String taskExchangeDetailIdStr) throws BusiException {
		StringBuilder activeNames = new StringBuilder();
		if(taskExchangeDetailIdStr == null || "".equals(taskExchangeDetailIdStr)){
			throw new BusiException("","方案ID丢失");
    	}
    	String[] activeItemIdArray = taskExchangeDetailIdStr.split("\\|");
    	if(activeItemIdArray == null || activeItemIdArray.length == 0){
    		throw new BusiException("","方案ID丢失");
    	}
    	if(activeItemIdArray.length > 100){
    		throw new BusiException("","批处理数据过多，最多同时操作50条记录");
    	}
    	List<String> taskIdList = Arrays.asList(activeItemIdArray);
    	List<TaskExchangeDetail> taskExchangeDetailList = activeDao.getTaskExchangeDetailList(taskIdList);
    	if(taskExchangeDetailList != null && taskExchangeDetailList.size() > 0){
    		Iterator it = taskExchangeDetailList.iterator();
    		while(it.hasNext()){
    			TaskExchangeDetail task = (TaskExchangeDetail)it.next();
    			if(task == null || CommonConstants.ACTIVE_ITEM_STATUS_NEW != task.getStatus()){
    				it.remove();
    				continue;
    			}
    			activeNames.append(task.getExchangeDetailId() + "|");
    			task.setStatus(CommonConstants.ACTIVE_ITEM_STATUS_START);//方案启动
    		}
    		activeDao.updateTaskExchangeDetailList(taskExchangeDetailList);  		
    	}
    	//准备日志数据
    	Map<String,String> logDataMap = new HashMap<String,String>();
    	logDataMap.put("activeName", activeNames.toString());
		return logDataMap;
		
	}
	/***
	 * @describe 启动活动
	 * @param taskIdStr
	 * @return
	 * @throws BusiException
	 */
	@Override
	public Map<String,String> startActive(String taskExchangeIdStr) throws BusiException {
		StringBuilder activeNames = new StringBuilder();
		if(taskExchangeIdStr == null || "".equals(taskExchangeIdStr)){
			throw new BusiException("","活动ID丢失");
    	}
    	String[] activeIdArray = taskExchangeIdStr.split("\\|");
    	if(activeIdArray == null || activeIdArray.length == 0){
    		throw new BusiException("","活动ID丢失");
    	}
    	if(activeIdArray.length > 100){
    		throw new BusiException("","批处理数据过多，最多同时操作100条记录");
    	}
    	List<String> taskIdList = Arrays.asList(activeIdArray);
    	List<TaskExchange> taskExchangeList = activeDao.getTaskExchangeList(taskIdList);
    	if(taskExchangeList != null && taskExchangeList.size() > 0){
    		Iterator it = taskExchangeList.iterator();
    		while(it.hasNext()){
    			TaskExchange task = (TaskExchange)it.next();
    			if(task == null || CommonConstants.ACTIVE_STATUS_NEW != task.getStatus()){
    				it.remove();
    				continue;
    			}
    			//校验活动是否有有效的方案
    			List<TaskExchangeDetail> detailList = activeDao.getDetailListByStatus(task.getExchangeId(), CommonConstants.ACTIVE_ITEM_STATUS_START);
    			if(detailList == null || detailList.size() == 0){
    				it.remove();
    				continue;
    			}
//    			List<TaskExchangeDetail> detailList = activeDao.findExchangeDetailListById(task.getExchangeId());
//    			if(detailList == null || detailList.size() == 0){
//    				it.remove();
//    				continue;
//    			}else{
//    				int flag = 0;
//    				for(TaskExchangeDetail detail : detailList){
//    					if(detail.getStatus() == CommonConstants.ACTIVE_ITEM_STATUS_START){
//    						flag = 1;
//    						break;
//    					}
//    				}
//    				if(flag == 0){
//    					it.remove();
//        				continue;
//    				}
//    			}
    			activeNames.append(task.getExchangeName() + "|");
    			task.setStatus(CommonConstants.ACTIVE_STATUS_START);//方案启动
    			task.setStartTime(new Date());
    		}
    		activeDao.updateTaskExchangeList(taskExchangeList);		
    	}
    	//准备日志数据
    	Map<String,String> logDataMap = new HashMap<String,String>();
    	logDataMap.put("activeName", activeNames.toString());
		return logDataMap;
	}

	@Override
	public Map<String, String> updateTaskExchangeStatus(String activeId, int status,
			String operate) throws BusiException {
		if(status != CommonConstants.ACTIVE_STATUS_NEW && status != CommonConstants.ACTIVE_STATUS_START 
				&& status != CommonConstants.ACTIVE_STATUS_STOP && status != CommonConstants.ACTIVE_STATUS_END){
			throw new BusiException("","活动状态参数传递错误");
		}
		TaskExchange taskExchange = activeDao.findTaskExchangeById(activeId);
		if(taskExchange == null){
			throw new BusiException("","该活动已经不存在");
		}
		//如果是暂停活动，活动一定要是已上线运行的
		if(CommonConstants.TASK_OPERATE_STOP.equals(operate)){
			if(CommonConstants.ACTIVE_STATUS_START != taskExchange.getStatus()){
				throw new BusiException("","该活动不能进行暂停,请确认后再操作");
			}
		//恢复活动
		}else if(CommonConstants.TASK_OPERATE_RECOVER.equals(operate)){
			if(CommonConstants.ACTIVE_STATUS_STOP != taskExchange.getStatus()){
				throw new BusiException("","该活动不能进行恢复,请确认后再操作");
			}
			Date endTime = taskExchange.getEndTime();
			if(endTime != null){
				Date now = new Date();
				if(now.after(endTime)){
					throw new BusiException("","该活动已经过期不能恢复");
				}
			}
			//校验活动是否有有效的方案
			List<TaskExchangeDetail> detailList = activeDao.getDetailListByStatus(taskExchange.getExchangeId(), CommonConstants.ACTIVE_ITEM_STATUS_START);
			if(detailList == null || detailList.size() == 0){
				throw new BusiException("","恢复活动失败,原因:当前活动没有有效的方案");
			}
			
		//结束活动，不能结束未发布的活动
		}else if(CommonConstants.TASK_OPERATE_END.equals(operate)){
			if(CommonConstants.ACTIVE_STATUS_NEW == taskExchange.getStatus()){
				throw new BusiException("","该活动还未发布,不能进行结束操作");
			}
		}else{
			throw new BusiException("","操作参数丢失");
		}
		//活动修改，方案跟着修改
		List<TaskExchangeDetail> detailList = activeDao.findExchangeDetailListById(activeId);
		if(detailList != null && detailList.size() > 0){
			for(TaskExchangeDetail detail : detailList){
				detail.setStatus(status);
			}
			activeDao.updateTaskExchangeDetailList(detailList);
		}
		taskExchange.setStatus(status);
		activeDao.updateTaskExchange(taskExchange);
		//准备日志数据
		Map<String,String> logDataMap = new HashMap<String,String>();
		logDataMap.put("activeName", taskExchange.getExchangeName());
		return logDataMap;
	}
	
	
	@Override
	public Map<String, String> updateTaskExchangeDetailStatus(String activeItemId, int status,
			String operate) throws BusiException {
		if(status != CommonConstants.ACTIVE_ITEM_STATUS_NEW && status != CommonConstants.ACTIVE_ITEM_STATUS_START 
				&& status != CommonConstants.ACTIVE_ITEM_STATUS_STOP && status != CommonConstants.ACTIVE_ITEM_STATUS_END){
			throw new BusiException("","方案状态参数传递错误");
		}
		TaskExchangeDetail detail = activeDao.findTaskExchangeDetailById(activeItemId);
		if(detail == null){
			throw new BusiException("","该方案已经不存在");
		}
		TaskExchange taskExchange = activeDao.findTaskExchangeById(detail.getExchangeId());
		if(taskExchange == null){
			throw new BusiException("","查找活动失败");
		}
		//获取方案所属的活动拥有的其他方案
		List<TaskExchangeDetail> notCurrentDetailList = activeDao.getNotCurrentDetailList(detail.getExchangeId(), detail.getExchangeDetailId());
		//如果是暂停方案，方案一定要是已上线运行的
		if(CommonConstants.TASK_OPERATE_STOP.equals(operate)){
			if(taskExchange.getStatus() == CommonConstants.ACTIVE_STATUS_START && (notCurrentDetailList == null || notCurrentDetailList.size() == 0)){
				throw new BusiException("","该方案不可暂停，原因：方案所属的活动只有当前一个有效方案");
			}
			if(CommonConstants.ACTIVE_ITEM_STATUS_START != detail.getStatus()){
				throw new BusiException("","该方案不能进行暂停,请确认后再操作");
			}
		//恢复方案
		}else if(CommonConstants.TASK_OPERATE_RECOVER.equals(operate)){
			if(CommonConstants.ACTIVE_ITEM_STATUS_STOP != detail.getStatus()){
				throw new BusiException("","该方案不能进行恢复,请确认后再操作");
			}			
		//结束方案，不能结束未发布的方案，未发布方案可以删除
		}else if(CommonConstants.TASK_OPERATE_END.equals(operate)){
			if(taskExchange.getStatus() == CommonConstants.ACTIVE_STATUS_START && (notCurrentDetailList == null || notCurrentDetailList.size() == 0)){
				throw new BusiException("","该方案不可结束，原因：方案所属的活动只有当前一个有效方案");
			}
			if(CommonConstants.ACTIVE_ITEM_STATUS_NEW == detail.getStatus()){
				throw new BusiException("","该方案还未发布,不能进行结束操作");
			}
		}else{
			throw new BusiException("","操作参数丢失");
		}
		detail.setStatus(status);
		activeDao.updateTaskExchangeDetail(detail);
		//准备日志数据
		Map<String,String> logDataMap = new HashMap<String,String>();
		logDataMap.put("activeName", detail.getExchangeDetailId());
		return logDataMap;
	}

	

	@Override
	public EasyuiDataGridJson activeItemList(EasyuiDataGrid dg,ExchangeVO exchangeVO) {
		return activeDao.activeItemList(dg, exchangeVO);
	}

	@Override
	public Map<String,String> saveTaskExchangeDetail(ExchangeVO exchangeVO) throws BusiException {
		String exchangeId = exchangeVO.getExchangeId();
		if(null == exchangeId || "" .equals(exchangeId)){
			throw new BusiException("","活动ID丢失");
		}
		TaskExchange taskExchange = activeDao.findTaskExchangeById(exchangeId);
		if(taskExchange == null){
			throw new BusiException("","查找活动失败");
		}
//		if(taskExchange.getStatus() != CommonConstants.ACTIVE_STATUS_NEW){
//			throw new BusiException("","当前活动不可进行编辑，请确认后操作");
//		}
		TaskExchangeDetail detail = new TaskExchangeDetail();
		String itemId = CommonBusiUtil.getActiveItemId();
		detail.setExchangeDetailId(itemId);
		detail.setExchangeId(exchangeId);
		detail.setExchangeDetail(exchangeVO.getExchangeDetail());
		detail.setOutAccountType(exchangeVO.getOutAccountType());
		detail.setOutAmountLow(exchangeVO.getOutAmountLow());
		detail.setOutAmountHigh(exchangeVO.getOutAmountHigh());
		detail.setInAccountType(exchangeVO.getInAccountType());
		detail.setInAmountRate(exchangeVO.getInAmountRate());
		detail.setStatus(CommonConstants.ACTIVE_ITEM_STATUS_NEW);
		detail.setTimes(exchangeVO.getTimes());
		Date date = new Date();
		detail.setCreateTime(date);
		detail.setUpdateTime(date);
		activeDao.saveTaskExchangeDetail(detail);
		Map<String,String> logMap = new HashMap<String,String>();
		logMap.put("itemId", itemId);
		logMap.put("activeName", taskExchange.getExchangeName());
		return logMap;
	}

	@Override
	public Map<String,String> editTaskExchangeDetail(ExchangeVO exchangeVO)
			throws BusiException {
		String exchangeDetailId = exchangeVO.getExchangeDetailId();
		if(null == exchangeDetailId || "" .equals(exchangeDetailId)){
			throw new BusiException("","方案ID丢失");
		}
		TaskExchangeDetail detail = activeDao.findTaskExchangeDetailById(exchangeDetailId);
		TaskExchange taskExchange = activeDao.findTaskExchangeById(detail.getExchangeId());
		if(detail == null){
			throw new BusiException("","查找方案失败");
		}
		if(taskExchange == null){
			throw new BusiException("","查找活动失败");
		}
		if(detail.getStatus() != CommonConstants.ACTIVE_ITEM_STATUS_NEW){
			throw new BusiException("","当前方案不可进行编辑，请确认后操作");
		}
		detail.setExchangeDetail(exchangeVO.getExchangeDetail());
		detail.setOutAccountType(exchangeVO.getOutAccountType());
		detail.setOutAmountLow(exchangeVO.getOutAmountLow());
		detail.setOutAmountHigh(exchangeVO.getOutAmountHigh());
		detail.setInAccountType(exchangeVO.getInAccountType());
		detail.setInAmountRate(exchangeVO.getInAmountRate());
		detail.setTimes(exchangeVO.getTimes());
		Date date = new Date();
		detail.setUpdateTime(date);
		activeDao.updateTaskExchangeDetail(detail);
		Map<String,String> logMap = new HashMap<String,String>();
		logMap.put("itemId", exchangeDetailId);
		logMap.put("activeName", taskExchange.getExchangeName());
		return logMap;
		
	}

}
