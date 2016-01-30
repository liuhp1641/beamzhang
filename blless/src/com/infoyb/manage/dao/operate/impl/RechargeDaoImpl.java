package com.cm.manage.dao.operate.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.IRechargeDao;
import com.cm.manage.model.account.RechargeManage;

@Repository
public class RechargeDaoImpl implements IRechargeDao {

	@Autowired
	private IBaseDao<Object> rechargeDao;
	@Override
	public List<RechargeManage> rechargeList() {
		StringBuffer str=new StringBuffer("select ID,CHANNEL_CODE,CHANNEL_NAME,LABEL,AD_NOTE,REMARK from ACCOUNT_RECHARGE_MANAGE ");
		List<Map> rechargeMapList=rechargeDao.findBySql(str.toString());
		List<RechargeManage> list=new ArrayList<RechargeManage>();
		if(rechargeMapList!=null&&rechargeMapList.size()>0){
			for (Map map : rechargeMapList) {
				RechargeManage bean=new RechargeManage();
				BigDecimal id=(BigDecimal) map.get("ID");
				bean.setId(id.intValue());
				bean.setChannelCode((String)map.get("CHANNEL_CODE"));
				bean.setChannelName((String)map.get("CHANNEL_NAME"));
				bean.setLabel((String)map.get("LABEL"));
				bean.setAdNote((String)map.get("AD_NOTE"));
				bean.setRemark((String)map.get("REMARK"));
				list.add(bean);
			}
		}
		return list;
	}

	@Override
	public void channelSave(RechargeManage manage) {
		StringBuffer str=new StringBuffer("UPDATE ACCOUNT_RECHARGE_MANAGE SET CHANNEL_CODE='");
		str.append(manage.getChannelCode());
		str.append("',CHANNEL_NAME='").append(manage.getChannelName()).append("',LABEL='").append(manage.getLabel()==null?"":manage.getLabel()).append("',AD_NOTE='");
		str.append(manage.getAdNote()==null?"":manage.getAdNote()).append("',REMARK='").append(manage.getRemark()).append("' ").append("where ID=").append(manage.getId());
		rechargeDao.executeSql(str.toString());
	}

}
