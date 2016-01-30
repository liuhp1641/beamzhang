package com.cm.manage.dao.report;

import java.util.List;

import com.cm.manage.model.report.TicketReport;

public interface ITicketReportDao {
	
	public void insertTicketReport();
	
    public List<TicketReport> ticketReportByMonth(Integer year,Integer month,String postCode);
	
	public List<TicketReport> ticketReportDetail(Integer year,Integer month,String postCode);

}
