package com.cm.manage.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cm.manage.service.report.IAccountReportService;
import com.cm.manage.service.report.IBetReportService;
import com.cm.manage.service.report.ITicketReportService;
import com.cm.manage.util.SpringContextUtils;
import com.cm.manage.util.base.DateUtil;


public class ReportJob extends QuartzJobBean {

    private Logger logger = Logger.getLogger(ReportJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Date date = new Date();
        logger.info("定时统计---ReportJob----" + DateUtil.format(date));
        try {
        	IBetReportService betReportService = (IBetReportService) SpringContextUtils.getBean("betReportService");
            if (betReportService != null ) {
            	betReportService.insertBetReport();
            }
            ITicketReportService ticketReportService=(ITicketReportService) SpringContextUtils.getBean("ticketReportService");
            if(ticketReportService!=null){
            	ticketReportService.insertTicketReport();
            }
            IAccountReportService accountReportService= (IAccountReportService)SpringContextUtils.getBean("accountReportService");
            if(accountReportService!=null){
            	accountReportService.insertAccountReport();
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
