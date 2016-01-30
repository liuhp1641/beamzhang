package com.cm.manage.service.report;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.report.ReportRecharge;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.account.DrawVO;
import com.cm.manage.vo.report.ReportRechargeVO;

public interface IReportRechargeService extends IBaseService {
	
	public boolean saveReportRecharge();

	public List<ReportRechargeVO> getReportRecharge(String year,String month);

	public List<ReportRechargeVO> reportRechargeDetail(String year, String month);

	public Map<String, Object> prepareExcelData(String year, String month);

}
