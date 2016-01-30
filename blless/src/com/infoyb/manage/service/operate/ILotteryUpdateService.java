package com.cm.manage.service.operate;

import java.util.List;

import com.cm.manage.model.order.LotteryUpdateLog;
import com.cm.manage.service.base.IBaseService;

public interface ILotteryUpdateService  extends IBaseService {
	/**
	 * 彩种更新记录
	 * @return
	 */
	public List<LotteryUpdateLog> lotteryUpdateList();
	
	/**
	 * 彩种更新保存
	 * @param manage
	 */
	public void lotteryUpdateSave(LotteryUpdateLog log);
	/**
	 * 彩种更新删除
	 * @param log
	 */
	public void lotteryUpdateDel(LotteryUpdateLog log);
}
