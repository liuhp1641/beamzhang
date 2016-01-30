package com.cm.manage.service.operate;

import java.util.List;

import com.cm.manage.model.account.RechargeManage;
import com.cm.manage.service.base.IBaseService;

public interface IRechargeService extends IBaseService {
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
