package com.cm.manage.service.operate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.ILotteryUpdateDao;
import com.cm.manage.model.order.LotteryUpdateLog;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.ILotteryUpdateService;

@Service("lotteryUpdateService")
public class LotteryUpdateServiceImpl extends BaseServiceImpl implements
		ILotteryUpdateService {

	@Autowired
	private ILotteryUpdateDao lotteryUpdateDao;
	@Override
	public List<LotteryUpdateLog> lotteryUpdateList() {
		return lotteryUpdateDao.lotteryUpdateList();
	}

	@Override
	public void lotteryUpdateSave(LotteryUpdateLog log) {
		lotteryUpdateDao.lotteryUpdateSave(log);

	}
	
	/**
	 * 彩种更新删除
	 * @param log
	 */
	public void lotteryUpdateDel(LotteryUpdateLog log){
		lotteryUpdateDao.lotteryUpdateDel(log);
	}

}
