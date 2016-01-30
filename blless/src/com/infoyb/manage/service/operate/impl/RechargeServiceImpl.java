package com.cm.manage.service.operate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.IRechargeDao;
import com.cm.manage.model.account.RechargeManage;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IRechargeService;
@Service("rechargeService")
public class RechargeServiceImpl extends BaseServiceImpl implements	IRechargeService {

	@Autowired
	private IRechargeDao rechargeDao;
	@Override
	public List<RechargeManage> rechargeList() {
		return rechargeDao.rechargeList();
	}

	@Override
	public void channelSave(RechargeManage manage) {
		rechargeDao.channelSave(manage);

	}

}
