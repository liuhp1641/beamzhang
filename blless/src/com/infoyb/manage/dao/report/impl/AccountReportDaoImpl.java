package com.cm.manage.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.report.IAccountReportDao;
import com.cm.manage.model.report.AccountReport;
import com.cm.manage.model.report.BetReport;
import com.cm.manage.util.base.DateUtil;

@Repository
public class AccountReportDaoImpl implements IAccountReportDao {
	
	@Autowired
	private IBaseDao<Object> accountReportDao;

	@Override
	public void insertAccountReport() {
		StringBuilder str=new StringBuilder("select u.member_type,a.event_type,a.second_type, sum(a.recharge_amount) recharge_amount, sum(a.bonus_amount) bonus_amount,");
        str.append(" sum(a.present_amount) present_amount,sum(a.score) score,sum(a.gold) gold from account_log a  inner join user_member u  on a.user_code = u.user_code ");
        str.append(" where to_char(a.create_time,'yyyy-MM-dd') = ? group by u.member_type, a.event_type, a.second_type order by u.member_type, a.event_type, a.second_type");
        List<Object> values = new ArrayList<Object>();
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time=DateUtil.format(date.getTime(),"yyyy-MM-dd");
		values.add(time);
		List<Map> list=accountReportDao.findBySql(str.toString(),values);
		if(list!=null&&list.size()>0){
			for(Map map :list){
				BigDecimal memberType=(BigDecimal) map.get("MEMBER_TYPE");
				BigDecimal eventType=(BigDecimal) map.get("EVENT_TYPE");
				String secondType=(String) map.get("SECOND_TYPE");
				BigDecimal rechargeAmount=map.get("RECHARGE_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("RECHARGE_AMOUNT");
				BigDecimal bonusAmount=map.get("BONUS_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BONUS_AMOUNT");
				BigDecimal presentAmount=map.get("PRESENT_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("PRESENT_AMOUNT");
				BigDecimal score=map.get("SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("SCORE");
				BigDecimal commission=map.get("COMMISSION")==null?new BigDecimal(0):(BigDecimal)map.get("COMMISSION");
				BigDecimal gold=map.get("GOLD")==null?new BigDecimal(0):(BigDecimal)map.get("GOLD");
				AccountReport report=new AccountReport();
				report.setBonusAmount(bonusAmount.doubleValue());
				report.setCommission(commission.doubleValue());
				report.setCreateTime(new Date());
				report.setEventType(eventType.intValue());
				report.setGold(gold.doubleValue());
				report.setLogDay(time);
				report.setMemberType(memberType.intValue());
				report.setPresentAmount(presentAmount.doubleValue());
				report.setRechargeAmount(rechargeAmount.doubleValue());
				report.setScore(score.doubleValue());
				report.setSecondType(secondType);
				accountReportDao.save(report);
			}
		}

	}
	public List<AccountReport> accountReportByMonth(Integer year,Integer month,Integer memberType){
		List<AccountReport> reportList=new ArrayList<AccountReport>();
		List<Map> mapList=new ArrayList<Map>();
        StringBuffer str=new StringBuffer(" select '上期余额' secondType,sum(RECHARGE_AMOUNT) rechargeAmount,sum(BONUS_AMOUNT) bonusAmount,sum(PRESENT_AMOUNT) presentAmount,sum(COMMISSION) commission,");
        str.append(" sum(SCORE) score,sum(GOLD) gold from ACCOUNT_DAY_LOG where DAY = ? and MEMBER_TYPE=? ");
        List<Object> values = new ArrayList<Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.add(Calendar.MONTH, -1);
		int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set( cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay);
		String preTime=DateUtil.format(cal.getTime(),"yyyy-MM-dd");
		values.add(preTime);
		values.add(memberType);
        List<Map> list=accountReportDao.findBySql(str.toString(), values);
        mapList.addAll(list);
        
		str=new StringBuffer(" select  '存入' secondType ,");
		str.append(" sum(RECHARGE_AMOUNT) rechargeAmount,sum(BONUS_AMOUNT) bonusAmount,sum(PRESENT_AMOUNT) presentAmount,sum(COMMISSION) commission,");
		str.append(" sum(SCORE) score,sum(GOLD) gold from REPORT_ACCOUNT where to_char(to_date(LOG_DAY, 'yyyy-MM-dd'),'yyyy-MM')= ? and MEMBER_TYPE=? and EVENT_TYPE=0 and SECOND_TYPE='00' ");
		values = new ArrayList<Object>();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		String time=DateUtil.format(cal.getTime(),"yyyy-MM");
		values.add(time);
		values.add(memberType);
		list=accountReportDao.findBySql(str.toString(), values);
		mapList.addAll(list);
		
		str=new StringBuffer(" select  '支出' secondType ,");
		str.append(" sum(RECHARGE_AMOUNT) rechargeAmount,sum(BONUS_AMOUNT) bonusAmount,sum(PRESENT_AMOUNT) presentAmount,sum(COMMISSION) commission,");
		str.append(" sum(SCORE) score,sum(GOLD) gold from REPORT_ACCOUNT where to_char(to_date(LOG_DAY, 'yyyy-MM-dd'),'yyyy-MM') = ? and MEMBER_TYPE=? and EVENT_TYPE=1 and SECOND_TYPE='00' ");
		values = new ArrayList<Object>();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		time=DateUtil.format(cal.getTime(),"yyyy-MM");
		values.add(time);
		values.add(memberType);
		list=accountReportDao.findBySql(str.toString(), values);
		mapList.addAll(list);
		
		str=new StringBuffer(" select '转入' secondType , ");
		str.append(" sum(RECHARGE_AMOUNT) rechargeAmount,sum(BONUS_AMOUNT) bonusAmount,sum(PRESENT_AMOUNT) presentAmount,sum(COMMISSION) commission,");
		str.append(" sum(SCORE) score,sum(GOLD) gold from REPORT_ACCOUNT where to_char(to_date(LOG_DAY, 'yyyy-MM-dd'),'yyyy-MM') = ? and MEMBER_TYPE=? and EVENT_TYPE=0 and SECOND_TYPE='01' ");
		values = new ArrayList<Object>();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		time=DateUtil.format(cal.getTime(),"yyyy-MM");
		values.add(time);
		values.add(memberType);
		list=accountReportDao.findBySql(str.toString(), values);
		mapList.addAll(list);
		
		str=new StringBuffer(" select '转出' secondType ,");
		str.append(" sum(RECHARGE_AMOUNT) rechargeAmount,sum(BONUS_AMOUNT) bonusAmount,sum(PRESENT_AMOUNT) presentAmount,sum(COMMISSION) commission,");
		str.append(" sum(SCORE) score,sum(GOLD) gold from REPORT_ACCOUNT where to_char(to_date(LOG_DAY, 'yyyy-MM-dd'),'yyyy-MM') = ? and MEMBER_TYPE=? and EVENT_TYPE=1 and SECOND_TYPE='01' ");
		values = new ArrayList<Object>();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		time=DateUtil.format(cal.getTime(),"yyyy-MM");
		values.add(time);
		values.add(memberType);
		list=accountReportDao.findBySql(str.toString(), values);
		mapList.addAll(list);
		
		str=new StringBuffer(" select '本期余额' secondType,sum(RECHARGE_AMOUNT) rechargeAmount,sum(BONUS_AMOUNT) bonusAmount,sum(PRESENT_AMOUNT) presentAmount,sum(COMMISSION) commission,");
		str.append(" sum(SCORE) score,sum(GOLD) gold from ACCOUNT_DAY_LOG where DAY= ? and MEMBER_TYPE=? ");
		
		values = new ArrayList<Object>();
		/*int currentMonth=new Date().getMonth()+1;
		if(currentMonth==month){
			cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -1);
			String currentTime=DateUtil.format(cal.getTime(),"yyyy-MM-dd");
			values.add(currentTime);
		}else{
			cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month-1);
			MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal.set( cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay);
			String currentTime=DateUtil.format(cal.getTime(),"yyyy-MM-dd");
			values.add(currentTime);
		}*/
		cal = Calendar.getInstance();
		String currentTime=DateUtil.format(cal.getTime(),"yyyy-MM-dd");
		values.add(currentTime);
		values.add(memberType);
		list=accountReportDao.findBySql(str.toString(), values);
		mapList.addAll(list);
		if(mapList!=null&&mapList.size()>0){
			for(Map map : mapList){
				String secondType=(String) map.get("SECONDTYPE");
				BigDecimal rechargeAmount=map.get("RECHARGEAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("RECHARGEAMOUNT");
				BigDecimal bonusAmount=map.get("BONUSAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BONUSAMOUNT");
				BigDecimal presentAmount=map.get("PRESENTAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("PRESENTAMOUNT");
				BigDecimal score=map.get("SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("SCORE");
				BigDecimal commission=map.get("COMMISSION")==null?new BigDecimal(0):(BigDecimal)map.get("COMMISSION");
				BigDecimal gold=map.get("GOLD")==null?new BigDecimal(0):(BigDecimal)map.get("GOLD");
				AccountReport report=new AccountReport();
				report.setBonusAmount(bonusAmount.doubleValue());
				report.setCommission(commission.doubleValue());
				report.setGold(gold.doubleValue());
				report.setMemberType(memberType.intValue());
				report.setPresentAmount(presentAmount.doubleValue());
				report.setRechargeAmount(rechargeAmount.doubleValue());
				report.setScore(score.doubleValue());
				report.setSecondType(secondType);
				reportList.add(report);
			}
		}
		return reportList;
	
	}

}
