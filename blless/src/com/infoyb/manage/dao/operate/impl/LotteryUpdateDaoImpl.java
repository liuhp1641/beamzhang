package com.cm.manage.dao.operate.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.ILotteryUpdateDao;
import com.cm.manage.model.order.LotteryUpdateLog;
import com.cm.manage.util.lottery.LotteryList;
import com.cm.manage.util.lottery.bean.LotteryClass;

@Repository
public class LotteryUpdateDaoImpl implements ILotteryUpdateDao {

	@Autowired
	private IBaseDao<LotteryUpdateLog> lotteryUpdateDao;

	@Autowired
	private IBaseDao<Object> baseDao;
	@Override
	public List<LotteryUpdateLog> lotteryUpdateList() {
		StringBuilder str=new StringBuilder(" select l.ID,l.LOTTERY_CODE,l.SID,l.PLATFORM,l.VERSION,l.MEMO,c.NAME channelName from TMS_LOTTERY_UPDATE_LOG l ");
		str.append(" left join USER_COOPERATION c on l.SID=c.SID");
		List<Map> updateLog = baseDao.findBySql(str.toString());
		List<LotteryUpdateLog> list=new ArrayList<LotteryUpdateLog>();
		if(updateLog!=null&&updateLog.size()>0){
			LotteryList lottery = new LotteryList();
			for(int i=0;i<updateLog.size();i++){
				Map map=updateLog.get(i);
				LotteryUpdateLog log=new LotteryUpdateLog();
				BigDecimal id=(BigDecimal) map.get("ID");
				log.setId(id.longValue());
				String lotteryCode = (String) map.get("LOTTERY_CODE");
				LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
				log.setLotteryCode(lotteryCode);
				log.setLotteryName(lotteryClass.getName());
				String sid=(String) map.get("SID");
				log.setSid(sid);
				String channelName=(String) map.get("CHANNELNAME");
				log.setChannelName(channelName);
				String platform=(String) map.get("PLATFORM");
				log.setPlatform(platform);
				String version=(String) map.get("VERSION");
				log.setVersion(version);
				String memo=(String) map.get("MEMO");
				log.setMemo(memo);
				list.add(log);
			}
		}
		return list;
	}

	@Override
	public void lotteryUpdateSave(LotteryUpdateLog log) {
		lotteryUpdateDao.saveOrUpdate(log);
	}
	
	/**
	 * 彩种更新删除
	 * @param log
	 */
	public void lotteryUpdateDel(LotteryUpdateLog log){
		lotteryUpdateDao.delete(log);
	}
}
