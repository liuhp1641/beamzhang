package com.cm.manage.quartz;

import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.cm.manage.service.report.IReportRechargeService;
import com.cm.manage.service.report.IReportWithdrawService;
import com.cm.manage.util.SpringContextUtils;
import com.cm.manage.util.base.DateUtil;

/**
 * 
 * @describe 每天定时向表插入统计数据
 */
public class ReportRechargeJob extends QuartzJobBean {

    private Logger logger = Logger.getLogger(ReportRechargeJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		Date date = new Date();
		logger.info("统计报表开始----" + DateUtil.format(date));
		try {
			IReportRechargeService reportRechargeService = (IReportRechargeService) SpringContextUtils
					.getBean("reportRechargeService");
			IReportWithdrawService reportWithdrawService = (IReportWithdrawService) SpringContextUtils
					.getBean("reportWithdrawService");
			try {
				if (reportRechargeService != null) {
					reportRechargeService.saveReportRecharge();
					logger.info("充值统计报表---ReportRechargeJob----JOB成功");
				}
			} catch (Exception e1) {
				logger.error(e1);
			} finally {
				try {
					if (reportWithdrawService != null) {
						reportWithdrawService.saveReportWithdraw();
						logger.info("提现统计报表---ReportWithdrawJob----JOB成功");
					}
				} catch (Exception e2) {
					logger.error(e2);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
    }
}
