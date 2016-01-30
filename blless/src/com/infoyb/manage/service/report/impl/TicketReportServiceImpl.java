package com.cm.manage.service.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.report.ITicketReportDao;
import com.cm.manage.model.report.TicketReport;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.report.ITicketReportService;
@Service("ticketReportService")
public class TicketReportServiceImpl extends BaseServiceImpl implements
		ITicketReportService {

	@Autowired
	private ITicketReportDao ticketReportDao;
	@Override
	public void insertTicketReport() {
		ticketReportDao.insertTicketReport();
	}

    public List<TicketReport> ticketReportByMonth(Integer year,Integer month,String postCode){
    	return ticketReportDao.ticketReportByMonth(year, month, postCode);
    }
	
	public List<TicketReport> ticketReportDetail(Integer year,Integer month,String postCode){
		return ticketReportDao.ticketReportDetail(year, month, postCode);
	}

}
