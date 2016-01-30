package com.cm.manage.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.report.IReportWithdrawDao;
import com.cm.manage.model.report.ReportWithdraw;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.report.ReportWithdrawVO;

@Repository("reportWithdrawDao")
public class ReportWithdrawDaoImpl implements IReportWithdrawDao {

	@Autowired
	private IBaseDao<Object> reportWithdrawDao;
	@Override
	public boolean saveReportWithdraw() {
		StringBuffer str = new StringBuffer(" select sum(a.amount) amount, sum(a.real_fee) fee ");
		str.append("  from account_draw a");
		str.append("  where a.status = 4");
		str.append("  and a.accept_time >= to_date(?, 'yyyy-mm-dd')");
		str.append("  and a.accept_time < to_date(?, 'yyyy-mm-dd')");

		List<Object> values = new ArrayList<Object>();
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		String endTime = DateUtil.format(date.getTime(), "yyyy-MM-dd");
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String beginTime = DateUtil.format(date.getTime(), "yyyy-MM-dd");
		values.add(beginTime);
		values.add(endTime);
//		 values.add("2014-11-21");
//		 values.add("2014-11-22");
		List<Map> resultMaps = reportWithdrawDao.findBySql(str.toString(),values);
		ReportWithdraw reportWithdraw = new ReportWithdraw();
		Map map = resultMaps.get(0);
		BigDecimal amount = map.get("AMOUNT") == null ? new BigDecimal(0): (BigDecimal) map.get("AMOUNT");
		BigDecimal fee = map.get("FEE") == null ? new BigDecimal(0): (BigDecimal) map.get("FEE");
		if (0 == amount.intValue() && (0 == fee.intValue())) {
			return true;
		} else {
			reportWithdraw.setSumApplyWithdraw(amount.doubleValue());
			reportWithdraw.setWithholdHandlingCharge(fee.doubleValue());
			reportWithdraw.setActualTransferAmount(amount.doubleValue()- fee.doubleValue());
			reportWithdraw.setReportDate(date.getTime());
			reportWithdrawDao.save(reportWithdraw);
			return true;
		}
	}

	@Override
	public List<ReportWithdrawVO> getReportWithdraw(String year,String month) {
		StringBuffer str=new StringBuffer(" select sum(t.withhold_handling_charge) withhold_handling_charge, ");
		str.append("  sum(t.sum_apply_withdraw) sum_apply_withdraw, ");
		str.append("  sum(t.actual_transfer_amount) actual_transfer_amount ");
		str.append("  from report_withdraw t where 1=1 ");
		str.append("  and t.report_date >= to_date(?, 'yyyy-mm') ");
		str.append("  and t.report_date < to_date(?, 'yyyy-mm') ");
		
		List<Object> values = new ArrayList<Object>();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, Integer.valueOf(year));
		date.set(Calendar.MONTH, Integer.valueOf(month));
		String endMonth=DateUtil.format(date.getTime(),"yyyy-MM");
		values.add(year+"-"+month);	
		values.add(endMonth);
		List<Map> reportWithdrawMaps=reportWithdrawDao.findBySql(str.toString(),values);
		
		List<ReportWithdrawVO> reportWithdrawList = new ArrayList<ReportWithdrawVO>();
        if (reportWithdrawMaps != null && reportWithdrawMaps.size() > 0) {// 转换模型
            for (Map map : reportWithdrawMaps) {
            	ReportWithdrawVO a = new ReportWithdrawVO();
            	BigDecimal sumApplyWithdraw = map.get("SUM_APPLY_WITHDRAW") == null ? new BigDecimal(0) : (BigDecimal) map.get("SUM_APPLY_WITHDRAW");
            	BigDecimal withholdHandlingCharge = map.get("WITHHOLD_HANDLING_CHARGE") == null ? new BigDecimal(0) : (BigDecimal) map.get("WITHHOLD_HANDLING_CHARGE");
            	BigDecimal actualTransferAmount = map.get("ACTUAL_TRANSFER_AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("ACTUAL_TRANSFER_AMOUNT");
            	a.setSumApplyWithdraw(sumApplyWithdraw.doubleValue());
            	a.setWithholdHandlingCharge(withholdHandlingCharge.doubleValue());
            	a.setActualTransferAmount(actualTransferAmount.doubleValue());
                Date reportDate = (Date) map.get("REPORT_DATE");
                a.setReportDate(DateUtil.format(reportDate));
                reportWithdrawList.add(a);
            }
        }
		return reportWithdrawList;
	}

	@Override
	public List<ReportWithdrawVO> getReportWithdrawDetail(String year,String month) {
		StringBuffer str=new StringBuffer("select t.withhold_handling_charge, t.sum_apply_withdraw,");
		str.append(" t.actual_transfer_amount, ");
		str.append(" t.report_date");
		str.append("  from report_withdraw t where 1=1 ");
		str.append(" and t.report_date >= to_date(?, 'yyyy-mm')");
		str.append(" and t.report_date < to_date(?, 'yyyy-mm')");
		str.append(" order by t.report_date");
		
		List<Object> values = new ArrayList<Object>();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, Integer.valueOf(year));
		date.set(Calendar.MONTH, Integer.valueOf(month));
		String endMonth=DateUtil.format(date.getTime(),"yyyy-MM");
		values.add(year+"-"+month);	
		values.add(endMonth);
		List<Map> reportWithdrawMaps=reportWithdrawDao.findBySql(str.toString(),values);
		
		List<ReportWithdrawVO> reportWithdrawList = new ArrayList<ReportWithdrawVO>();
        if (reportWithdrawMaps != null && reportWithdrawMaps.size() > 0) {// 转换模型
            for (Map map : reportWithdrawMaps) {
            	ReportWithdrawVO a = new ReportWithdrawVO();
            	BigDecimal sumApplyWithdraw = map.get("SUM_APPLY_WITHDRAW") == null ? new BigDecimal(0) : (BigDecimal) map.get("SUM_APPLY_WITHDRAW");
            	BigDecimal withholdHandlingCharge = map.get("WITHHOLD_HANDLING_CHARGE") == null ? new BigDecimal(0) : (BigDecimal) map.get("WITHHOLD_HANDLING_CHARGE");
            	BigDecimal actualTransferAmount = map.get("ACTUAL_TRANSFER_AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("ACTUAL_TRANSFER_AMOUNT");
            	a.setSumApplyWithdraw(sumApplyWithdraw.doubleValue());
            	a.setWithholdHandlingCharge(withholdHandlingCharge.doubleValue());
            	a.setActualTransferAmount(actualTransferAmount.doubleValue());
                Date reportDate = (Date) map.get("REPORT_DATE");
                a.setReportDate(DateUtil.format(reportDate,"yyyy-MM-dd"));
                reportWithdrawList.add(a);
            }
        }
		return reportWithdrawList;
	}

	@Override
	public Map<String, Object> prepareExcelData(String year, String month) {
		List<ReportWithdrawVO> reportWithdrawList =this.getReportWithdrawDetail(year, month);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("drawList", reportWithdrawList);
		String date=year+"-"+month;
		resMap.put("date", date);
		return resMap;
	}
	
	}
