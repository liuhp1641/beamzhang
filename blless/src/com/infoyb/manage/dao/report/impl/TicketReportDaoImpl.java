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
import com.cm.manage.dao.report.ITicketReportDao;
import com.cm.manage.model.report.TicketReport;
import com.cm.manage.util.base.DateUtil;
import com.cm.order.http.IOrderManageHttpService;
import com.cm.order.http.bean.TicketCenterBean;
@Repository
public class TicketReportDaoImpl implements ITicketReportDao {

	@Autowired
	private IBaseDao<Object> ticketReportDao;
	
	@Autowired
	private IOrderManageHttpService orderInterface;
	
	@Override
	public void insertTicketReport() {

		List<TicketCenterBean> ticketBean = orderInterface.ticketCenterBalance();
		if(ticketBean!=null&&ticketBean.size()>0){
			for( TicketCenterBean bean : ticketBean){
				String postCode=bean.getPostCode();
				StringBuffer str=new StringBuffer("select sum(t.amount) orderAmount from tms_ticket t,tms_programs tp  where t.programs_order_id = tp.programs_order_id and t.ticket_status = 3 and to_char(tp.send_time,'yyyy-MM-dd') = ? and t.post_code = ? ");
				List<Object> values = new ArrayList<Object>();
				Date beginDate = new Date();
				Calendar date = Calendar.getInstance();
				date.setTime(beginDate);
				date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
				String time=DateUtil.format(date.getTime(),"yyyy-MM-dd");
				values.add(time);
				values.add(postCode);
				List<Map> list=ticketReportDao.findBySql(str.toString(),values);
				BigDecimal orderAmount=new BigDecimal(0);
				if(list!=null&&list.size()>0){
					Map map=list.get(0);
					orderAmount= map.get("ORDERAMOUNT")==null?new BigDecimal(0): (BigDecimal)map.get("ORDERAMOUNT");
					
				}
				BigDecimal bonusAmount=new BigDecimal(0);
				str=new StringBuffer("select sum(t.bonus_amount) bonusAmount from tms_ticket t,tms_programs tp where t.programs_order_id = tp.programs_order_id  and t.bonus_status = 1  and to_char(tp.bonus_time,'yyyy-MM-dd') = ?  and t.post_code = ? ");
			    list=ticketReportDao.findBySql(str.toString(),values);
				if(list!=null&&list.size()>0){
					Map map=list.get(0);
					bonusAmount=map.get("BONUSAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BONUSAMOUNT");
					
				}
				BigDecimal partOrder=new BigDecimal(0);
				str=new StringBuffer("select sum(t.amount) partOrder from tms_ticket t, tms_programs tp  where t.programs_order_id = tp.programs_order_id  and tp.order_status != 1 and t.ticket_status = 3 and to_char(tp.send_time, 'yyyy-MM-dd') = ? and t.post_code = ? ");
			    list=ticketReportDao.findBySql(str.toString(),values);
				if(list!=null&&list.size()>0){
					Map map=list.get(0);
					partOrder= map.get("PARTORDER")==null?new BigDecimal(0):(BigDecimal)map.get("PARTORDER");
					
				}
				
				BigDecimal partBonus=new BigDecimal(0);
				str=new StringBuffer("select sum(t.bonus_amount) partBonus from tms_ticket t,tms_programs tp 	where t.programs_order_id = tp.programs_order_id  and tp.order_status != 1  and t.bonus_status = 1   and to_char(tp.bonus_time,'yyyy-MM-dd') = ?  and t.post_code = ? ");
			    list=ticketReportDao.findBySql(str.toString(),values);
				if(list!=null&&list.size()>0){
					Map map=list.get(0);
					partBonus= map.get("PARTBONUS")==null?new BigDecimal(0):(BigDecimal) map.get("PARTBONUS");
					
				}
				BigDecimal noOrder=new BigDecimal(0);
				str=new StringBuffer("select sum(t.amount) noOrder from tms_ticket t, tms_programs tp where t.programs_order_id = tp.programs_order_id and tp.send_status = 1 and instr(t.err_code,'E9999') > 0 and to_char(tp.send_time, 'yyyy-MM-dd') = ? and t.post_code = ? ");
			    list=ticketReportDao.findBySql(str.toString(),values);
				if(list!=null&&list.size()>0){
					Map map=list.get(0);
					noOrder= map.get("NOORDER")==null?new BigDecimal(0):(BigDecimal) map.get("NOORDER");
					
				}
				
				BigDecimal noBonus=new BigDecimal(0);
				str=new StringBuffer("select sum(t.bonus_amount) noBonus from tms_ticket t, tms_programs tp where t.programs_order_id = tp.programs_order_id and tp.send_status = 1 and t.bonus_status = 1 and instr(t.err_code,'E9999') > 0 and to_char(tp.bonus_time, 'yyyy-MM-dd') = ? and t.post_code = ? ");
			    list=ticketReportDao.findBySql(str.toString(),values);
				if(list!=null&&list.size()>0){
					Map map=list.get(0);
					noBonus=map.get("NOBONUS")==null?new BigDecimal(0):(BigDecimal) map.get("NOBONUS");
					
				}
				TicketReport ticketReport=new TicketReport();
				ticketReport.setPostCode(postCode);
				ticketReport.setBetAmount(orderAmount.doubleValue());
				ticketReport.setBonusAmount(bonusAmount.doubleValue());
				ticketReport.setPartAmount(partOrder.add(partBonus).doubleValue());
				ticketReport.setNoReceipeAmount(noOrder.add(noBonus).doubleValue());
				ticketReport.setLogDay(time);
				ticketReport.setCreateTime(new Date());
				ticketReportDao.save(ticketReport);
			}
		}
	}
	
    public List<TicketReport> ticketReportByMonth(Integer year,Integer month,String postCode){

		StringBuffer str=new StringBuffer("select sum(BET_AMOUNT) betAmount,sum(BONUS_AMOUNT) bonusAmount,sum(PART_AMOUNT) partAmount,sum(NO_RECEIPE_AMOUNT) noReceipeAmount from REPORT_TICKET where  to_char(to_date(LOG_DAY, 'yyyy-MM-dd'),'yyyy-MM') = ? and POST_CODE=? ");
		List<Object> values = new ArrayList<Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		String time=DateUtil.format(cal.getTime(),"yyyy-MM");
		values.add(time);
		values.add(postCode);
		List<Map> list=ticketReportDao.findBySql(str.toString(), values);
		List<TicketReport> reportList=new ArrayList<TicketReport>();
		if(list!=null&&list.size()>0){
			for(Map map : list){
				TicketReport report=new TicketReport();
				BigDecimal betAmount=map.get("BETAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BETAMOUNT");
				BigDecimal bonusAmount=map.get("BONUSAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BONUSAMOUNT");
				BigDecimal partAmount=map.get("PARTAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("PARTAMOUNT");
				BigDecimal noReceipeAmount=map.get("NORECEIPEAMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("NORECEIPEAMOUNT");
				report.setBetAmount(betAmount.doubleValue());
				report.setBonusAmount(bonusAmount.doubleValue());
				report.setPartAmount(partAmount.doubleValue());
				report.setNoReceipeAmount(noReceipeAmount.doubleValue());
				reportList.add(report);
			}
		}
		return reportList;
	
    }
	
	public List<TicketReport> ticketReportDetail(Integer year,Integer month,String postCode){

		StringBuffer str=new StringBuffer("select LOG_DAY,BET_AMOUNT ,BONUS_AMOUNT,PART_AMOUNT,NO_RECEIPE_AMOUNT  from REPORT_TICKET where  to_char(to_date(LOG_DAY, 'yyyy-MM-dd'),'yyyy-MM') = ? and POST_CODE=? order by  LOG_DAY ");
		List<Object> values = new ArrayList<Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		String time=DateUtil.format(cal.getTime(),"yyyy-MM");
		values.add(time);
		values.add(postCode);
		List<Map> list=ticketReportDao.findBySql(str.toString(), values);
		List<TicketReport> reportList=new ArrayList<TicketReport>();
		if(list!=null&&list.size()>0){
			for(Map map : list){
				TicketReport report=new TicketReport();
				BigDecimal betAmount=map.get("BET_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BET_AMOUNT");
				BigDecimal bonusAmount=map.get("BONUS_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BONUS_AMOUNT");
				BigDecimal partAmount=map.get("PART_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("PART_AMOUNT");
				BigDecimal noReceipeAmount=map.get("NO_RECEIPE_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("NO_RECEIPE_AMOUNT");
				report.setBetAmount(betAmount.doubleValue());
				report.setBonusAmount(bonusAmount.doubleValue());
				report.setPartAmount(partAmount.doubleValue());
				report.setNoReceipeAmount(noReceipeAmount.doubleValue());
				String logDay = (String) map.get("LOG_DAY");
				report.setLogDay(logDay);
				reportList.add(report);
			}
		}
		return reportList;
	
	}
}
