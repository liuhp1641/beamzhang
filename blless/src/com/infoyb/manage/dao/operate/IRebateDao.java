package com.cm.manage.dao.operate;

import java.util.Map;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.TaskRebateUserVO;
import com.cm.manage.vo.operate.TaskRebateVO;

public interface IRebateDao {
	/**
	 * 返利方案列表
	 * @param dg
	 * @return
	 */
	public EasyuiDataGridJson rebateList(EasyuiDataGrid dg,TaskRebateVO rebateVo) ;
	/**
	 * 返利方案保存
	 * @param rebate
	 * @return
	 */
	public boolean saveRebate(TaskRebateVO rebate);
	
	/**
	 * 判断返利方案是否有重复
	 * @param rebate
	 * @return
	 */
	public TaskRebateVO isExistRebate(String lotteryCodes,Integer vipLow,Integer vipHigh,String startTime,String endTime) ;
	/**
	 * 更改返利方案的状态
	 * @param rebateId
	 * @param status
	 * @return
	 */
	public boolean updateActivityStatus(String rebateId,Integer status);
	
	/**
	 * 返利方案开始
	 * @param rebateId
	 * @return
	 */
	public boolean activityStart(String rebateId);
	/**
	 * 返利方案详情
	 * @param rebateId
	 * @return
	 */
	public TaskRebateVO rebateInfo(String rebateId);
	/**
	 * 返利方案日志列表
	 * @param dg
	 * @param rebateUser
	 * @return
	 */
	public EasyuiDataGridJson rebateLog(EasyuiDataGrid dg,TaskRebateUserVO rebateUser) ;
	/**
	 * 返利日志汇总
	 * @param rebateUser
	 * @return
	 */
	public Map rebateCount(TaskRebateUserVO rebateUser);
}
