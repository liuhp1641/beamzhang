package com.cm.manage.service.operate;

import java.util.List;
import java.util.Map;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.HandworkBonusVO;
import com.cm.manage.vo.order.MainIssueVO;

public interface IOperateService extends IBaseService{
	
	/**
	 * 派奖查询
	 * @param dg
	 * @param bonus
	 * @return
	 */
	 public EasyuiDataGridJson handBonusList(EasyuiDataGrid dg, HandworkBonusVO bonus);
	 
	 /**
	  * 派奖取消
	  * @param id
	  * @return
	  */
	 public boolean handBonusCancel(String programsOrderId);
	 
	 /**
	  * 线下派奖/派奖还原
	  * @param programsList
	  * @param status
	  * @return
	  */
	 public boolean handBonusUpdate(List<String> programsList, Integer status);

	 /**
	  * 奖期查询
	  * @param dg
	  * @param issue
	  * @return
	  */
	 public EasyuiDataGridJson issueList(EasyuiDataGrid dg, MainIssueVO issue);
	 
	 /**
	  * 期号详情
	  * @param lotteryCode
	  * @param name
	  * @return
	  */
	 public Map issueDetail(String lotteryCode,String name);
	 
	 /**
	  * 奖期编辑公告保存
	  * @param lotteryCode
	  * @param name
	  * @param bonusNumber
	  * @param saleTotal
	  * @param bonusPool
	  * @param bonusClass
	  * @return
	  */
	 public boolean awardSetSave(String lotteryCode, String name,String bonusNumber,String saleTotal,String prizePool,String bonusClass);



}
