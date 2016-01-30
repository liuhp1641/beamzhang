package com.cm.manage.service.operate.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.IOperateDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IOperateService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.HandworkBonusVO;
import com.cm.manage.vo.order.MainIssueVO;

@Service("operateService")
public class OperateServiceImpl extends BaseServiceImpl implements
		IOperateService {
	
	@Autowired
	private IOperateDao operateDao;

	@Override
	public EasyuiDataGridJson handBonusList(EasyuiDataGrid dg,
			HandworkBonusVO bonus) {
		
		return operateDao.handBonusList(dg, bonus);
	}

	 /**
	  * 派奖取消
	  * @param id
	  * @return
	  */
	 public boolean handBonusCancel(String programsOrderId){
		 return operateDao.handBonusCancel(programsOrderId);
	 }
	 
	 /**
	  * 线下派奖/派奖还原
	  * @param programsList
	  * @param status
	  * @return
	  */
	 public boolean handBonusUpdate(List<String> programsList, Integer status){
		 return operateDao.handBonusUpdate(programsList, status);
	 }

	 /**
	  * 奖期查询
	  * @param dg
	  * @param issue
	  * @return
	  */
	 public EasyuiDataGridJson issueList(EasyuiDataGrid dg, MainIssueVO issue){
		 return operateDao.issueList(dg, issue);
	 }
	 /**
	  * 期号详情
	  * @param lotteryCode
	  * @param name
	  * @return
	  */
	 public Map issueDetail(String lotteryCode,String name){
		 return operateDao.issueDetail(lotteryCode, name);
	 }
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
	 public boolean awardSetSave(String lotteryCode, String name,String bonusNumber,String saleTotal,String prizePool,String bonusClass){
		 return operateDao.awardSetSave(lotteryCode, name, bonusNumber, saleTotal, prizePool, bonusClass);
	 }
}
