package com.cm.manage.dao.report;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.report.ReportWithdraw;
import com.cm.manage.vo.report.ReportWithdrawVO;


public interface  IReportWithdrawDao {
	
	public boolean saveReportWithdraw();

	public List<ReportWithdrawVO> getReportWithdraw(String year,String month);

	public List<ReportWithdrawVO> getReportWithdrawDetail(String year,String month);

	public Map<String, Object> prepareExcelData(String year, String month);

}
