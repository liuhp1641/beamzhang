package com.cm.manage.dao.report;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.report.ReportRecharge;
import com.cm.manage.vo.report.ReportRechargeVO;


public interface IReportRechargeDao {
	
	public boolean saveReportRecharge();

	public List<ReportRechargeVO> getReportRecharge(String year,String month);

	public List<ReportRechargeVO> getReportRechargeDetail(String year,String month);

	public Map<String, Object> prepareExcelData(String year, String month);

}
