package com.cm.manage.dao.operate;

import java.util.List;

import com.cm.manage.model.account.RechargeManage;

public interface IRechargeDao {

	/**
	 * 充值管理支付列表
	 * @return
	 */
	public List<RechargeManage> rechargeList();
	
	/**
	 * 支付方式保存
	 * @param manage
	 */
	public void channelSave(RechargeManage manage);
	
}
