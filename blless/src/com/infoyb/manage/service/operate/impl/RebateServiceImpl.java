package com.cm.manage.service.operate.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.IRebateDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IRebateService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.TaskRebateUserVO;
import com.cm.manage.vo.operate.TaskRebateVO;
@Service("rebateService")
public class RebateServiceImpl extends BaseServiceImpl implements
		IRebateService {
	@Autowired
	private IRebateDao rebateDao;
	
	/**
	 * 返利方案列表
	 * @param dg
	 * @return
	 */
	@Override
	public EasyuiDataGridJson rebateList(EasyuiDataGrid dg,TaskRebateVO rebateVo) {
		return rebateDao.rebateList(dg,rebateVo);
	}
	
	/**
	 * 返利方案保存
	 * @param rebate
	 * @return
	 */
	public boolean saveRebate(TaskRebateVO rebate){
		return rebateDao.saveRebate(rebate);
	}
	/**
	 * 判断返利方案是否有重复
	 * @param rebate
	 * @return
	 */
	public TaskRebateVO isExistRebate(String lotteryCodes,Integer vipLow,Integer vipHigh,String startTime,String endTime){
		return rebateDao.isExistRebate(lotteryCodes, vipLow, vipHigh, startTime, endTime);
	}
	
	/**
	 * 更改返利方案的状态
	 * @param rebateId
	 * @param status
	 * @return
	 */
	public boolean updateActivityStatus(String rebateId,Integer status){
		return rebateDao.updateActivityStatus(rebateId, status);
	}

	/**
	 * 返利方案开始
	 * @param rebateId
	 * @return
	 */
	public boolean activityStart(String rebateId){
		return rebateDao.activityStart(rebateId);
	}
	/**
	 * 返利方案详情
	 * @param rebateId
	 * @return
	 */
	public TaskRebateVO rebateInfo(String rebateId){
		return rebateDao.rebateInfo(rebateId);
	}
	
	/**
	 * 返利方案日志列表
	 * @param dg
	 * @param rebateUser
	 * @return
	 */
	public EasyuiDataGridJson rebateLog(EasyuiDataGrid dg,TaskRebateUserVO rebateUser) {
		return rebateDao.rebateLog(dg, rebateUser);
	}
	/**
	 * 返利日志汇总
	 * @param rebateUser
	 * @return
	 */
	public Map rebateCount(TaskRebateUserVO rebateUser){
		return rebateDao.rebateCount(rebateUser);
	}
}
