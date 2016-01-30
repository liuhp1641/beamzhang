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
import com.cm.manage.dao.report.IBetReportDao;
import com.cm.manage.model.report.BetReport;
import com.cm.manage.util.base.DateUtil;

@Repository
public class BetReportDaoImpl implements IBetReportDao {

	@Autowired
	private IBaseDao<Object> betReportDao;
	@Override
	public void insertBetReport() {

		StringBuffer str=new StringBuffer("select sum(order_amount) orderAmount from tms_order  where order_status = 0  and to_char(create_time,'yyyy-MM-dd') = ? ");
		List<Object> values = new ArrayList<Object>();
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time=DateUtil.format(date.getTime(),"yyyy-MM-dd");
		values.add(time);
		List<Map> list=betReportDao.findBySql(str.toString(),values);
		BigDecimal orderAmount=new BigDecimal(0);
		if(list!=null&&list.size()>0){
			Map map=list.get(0);
			orderAmount=map.get("ORDERAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("ORDERAMOUNT");
			
		}
		BigDecimal lastAmount=new BigDecimal(0);
		str=new StringBuffer("select sum(last_amount) lastAmount from tms_programs where order_status in (-1,0,1)  and bonus_status = 0  and last_amount > 0  and to_char(create_time,'yyyy-MM-dd') = ?");
	    list=betReportDao.findBySql(str.toString(),values);
		if(list!=null&&list.size()>0){
			Map map=list.get(0);
			lastAmount= map.get("LASTAMOUNT")==null?new BigDecimal(0): (BigDecimal)map.get("LASTAMOUNT");
			
		}
		BigDecimal ticketAmount=new BigDecimal(0);
		str=new StringBuffer("select sum(t.amount) ticketAmount from tms_ticket t,tms_programs tp where tp.programs_order_id = t.programs_order_id 	and t.ticket_status = 3 and to_char(tp.return_time,'yyyy-MM-dd') = ? ");
	    list=betReportDao.findBySql(str.toString(),values);
		if(list!=null&&list.size()>0){
			Map map=list.get(0);
			ticketAmount= map.get("TICKETAMOUNT")==null?new BigDecimal(0):(BigDecimal) map.get("TICKETAMOUNT");
			
		}
		
		BetReport betReport=new BetReport();
		betReport.setLogDay(time);
		betReport.setBetAmount(orderAmount.add(lastAmount).doubleValue());
		betReport.setTicketAmount(ticketAmount.doubleValue());
		betReport.setCreateTime(new Date());
		betReportDao.save(betReport);
	}

	public List<BetReport> betReportByMonth(Integer year,Integer month){
		StringBuffer str=new StringBuffer("select sum(BET_AMOUNT) betAmount,sum(TICKET_AMOUNT) ticketAmount from REPORT_BET where  to_char(to_date(LOG_DAY, 'yyyy-MM-dd'),'yyyy-MM') = ?");
		List<Object> values = new ArrayList<Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		String time=DateUtil.format(cal.getTime(),"yyyy-MM");
		values.add(time);
		List<Map> list=betReportDao.findBySql(str.toString(), values);
		List<BetReport> reportList=new ArrayList<BetReport>();
		if(list!=null&&list.size()>0){
			for(Map map : list){
				BetReport report=new BetReport();
				BigDecimal betAmount=map.get("BETAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BETAMOUNT");
				BigDecimal ticketAmount=map.get("TICKETAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("TICKETAMOUNT");
				report.setBetAmount(betAmount.doubleValue());
				report.setTicketAmount(ticketAmount.doubleValue());
				reportList.add(report);
			}
		}
		return reportList;
	}
	
	public List<BetReport> betReportDetail(Integer year,Integer month){
		StringBuffer str=new StringBuffer("select LOG_DAY,BET_AMOUNT ,TICKET_AMOUNT  from REPORT_BET where  to_char(to_date(LOG_DAY, 'yyyy-MM-dd'),'yyyy-MM') = ? order by  LOG_DAY ");
		List<Object> values = new ArrayList<Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		String time=DateUtil.format(cal.getTime(),"yyyy-MM");
		values.add(time);
		List<Map> list=betReportDao.findBySql(str.toString(), values);
		List<BetReport> reportList=new ArrayList<BetReport>();
		if(list!=null&&list.size()>0){
			for(Map map : list){
				BetReport report=new BetReport();
				BigDecimal betAmount=map.get("BET_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BET_AMOUNT");
				BigDecimal ticketAmount=map.get("TICKET_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("TICKET_AMOUNT");
				report.setBetAmount(betAmount.doubleValue());
				report.setTicketAmount(ticketAmount.doubleValue());
				String logDay = (String) map.get("LOG_DAY");
				report.setLogDay(logDay);
				reportList.add(report);
			}
		}
		return reportList;
	}
}
