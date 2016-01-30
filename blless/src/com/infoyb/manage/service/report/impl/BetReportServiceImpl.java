package com.cm.manage.service.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.report.IBetReportDao;
import com.cm.manage.model.report.BetReport;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.report.IBetReportService;

@Service("betReportService")
public class BetReportServiceImpl extends BaseServiceImpl implements
		IBetReportService {

	@Autowired
	private IBetReportDao betReportDao;
	@Override
	public void insertBetReport() {
		betReportDao.insertBetReport();
	}

   public List<BetReport> betReportByMonth(Integer year,Integer month){
	   return betReportDao.betReportByMonth(year, month);
   }
	
	public List<BetReport> betReportDetail(Integer year,Integer month){
		return betReportDao.betReportDetail(year, month);
	}
}
