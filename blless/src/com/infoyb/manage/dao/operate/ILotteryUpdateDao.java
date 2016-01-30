package com.cm.manage.dao.operate;

import java.util.List;

import com.cm.manage.model.order.LotteryUpdateLog;

public interface ILotteryUpdateDao {

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
