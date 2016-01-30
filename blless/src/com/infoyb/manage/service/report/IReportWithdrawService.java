package com.cm.manage.service.report;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.report.ReportWithdraw;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.report.ReportWithdrawVO;

public interface IReportWithdrawService extends IBaseService {
	
	public boolean saveReportWithdraw();

	public List<ReportWithdrawVO> getReportWithdraw(String year,String month);

	public List<ReportWithdrawVO> reportWithdrawDetail(String year, String month);

	public Map<String, Object> prepareExcelData(String year, String month);

}
