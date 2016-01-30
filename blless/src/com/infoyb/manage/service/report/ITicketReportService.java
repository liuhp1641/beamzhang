package com.cm.manage.service.report;

import java.util.List;

import com.cm.manage.model.report.TicketReport;
import com.cm.manage.service.base.IBaseService;

public interface ITicketReportService extends IBaseService {
	public void insertTicketReport();
	
    public List<TicketReport> ticketReportByMonth(Integer year,Integer month,String postCode);
	
	public List<TicketReport> ticketReportDetail(Integer year,Integer month,String postCode);

}
