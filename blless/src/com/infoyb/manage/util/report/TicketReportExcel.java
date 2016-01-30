package com.cm.manage.util.report;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cm.manage.model.report.TicketReport;
import com.cm.manage.util.base.DateUtil;

public class TicketReportExcel {

		/***
		 * @describe Excel数据
		 * @param resMap
		 * @param workbook
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static HSSFSheet buildTicketExcelData(Map<String,Object> resMap,HSSFWorkbook workbook){
			HSSFSheet sheet = workbook.createSheet("出票统计");
			HSSFRow header0 = sheet.createRow(0);//第一行
			header0.createCell(0).setCellValue("#彩米后台出票统计明细查询");
			HSSFRow header1 = sheet.createRow(1);//第二行
			Integer year =(Integer) resMap.get("year");
			Integer month=(Integer) resMap.get("month");
			String postName=(String) resMap.get("postName");
			header1.createCell(0).setCellValue("#年:[" + year + "]月：["+month+"] 出票口:["+postName+"]");
			HSSFRow header2 = sheet.createRow(2);//第四行
			header2.createCell(0).setCellValue("#-----------------------------------------明细列表----------------------------------------");
			HSSFRow header3 = sheet.createRow(3);//第五行
			header3.createCell(0).setCellValue("日期");
			header3.createCell(1).setCellValue("投注出票金额");
			header3.createCell(2).setCellValue("部分成交撤单金额");
			header3.createCell(3).setCellValue("未回执撤单金额");
			header3.createCell(4).setCellValue("返奖金额");
			List<TicketReport> ticketList = (List<TicketReport>)resMap.get("ticketList");
			int rownum = 4;
			if(ticketList != null && ticketList.size() > 0){
				for(TicketReport report : ticketList){
					HSSFRow row = sheet.createRow(rownum++);
					row.createCell(0).setCellValue(report.getLogDay());
					row.createCell(1).setCellValue(report.getBetAmount());
					row.createCell(2).setCellValue(report.getPartAmount());
					row.createCell(3).setCellValue(report.getNoReceipeAmount());
					row.createCell(4).setCellValue(report.getBonusAmount());
				}
			}
			HSSFRow nextRow = sheet.createRow(rownum++);
			nextRow.createCell(0).setCellValue("#-----------------------------------------明细列表结束------------------------------------");
			Date date = new Date();
			String now = DateUtil.format(date,"yyyy年MM月dd日 HH:mm:ss");
			nextRow = sheet.createRow(rownum++);
			nextRow.createCell(0).setCellValue("#导出时间：[" + now + "]");
			return sheet;
		}
		
}
