package com.cm.manage.util.report;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cm.manage.model.report.AccountReport;
import com.cm.manage.util.base.DateUtil;

public class AccountReportExcel {

		/***
		 * @describe Excel数据
		 * @param resMap
		 * @param workbook
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static HSSFSheet buildAccountExcelData(Map<String,Object> resMap,HSSFWorkbook workbook){
			Integer year =(Integer) resMap.get("year");
			Integer month=(Integer) resMap.get("month");
			String memberType=(String) resMap.get("memberType");
			HSSFSheet sheet = workbook.createSheet("账户统计");
			HSSFRow header0 = sheet.createRow(0);//第一行
			header0.createCell(0).setCellValue("#彩米后台["+memberType+"]统计明细查询");
			HSSFRow header1 = sheet.createRow(1);//第二行
			header1.createCell(0).setCellValue("#年:[" + year + "]月：["+month+"]");
			HSSFRow header2 = sheet.createRow(2);//第四行
			header2.createCell(0).setCellValue("#-----------------------------------------明细列表----------------------------------------");
			HSSFRow header3 = sheet.createRow(3);//第五行
			header3.createCell(0).setCellValue("类型");
			header3.createCell(1).setCellValue("充值金");
			header3.createCell(2).setCellValue("奖金");
			header3.createCell(3).setCellValue("红包");
			header3.createCell(4).setCellValue("积分");
			header3.createCell(5).setCellValue("金币");
			List<AccountReport> accountList = (List<AccountReport>)resMap.get("accountList");
			int rownum = 4;
			if(accountList != null && accountList.size() > 0){
				for(AccountReport report : accountList){
					HSSFRow row = sheet.createRow(rownum++);
					row.createCell(0).setCellValue(report.getSecondType());
					row.createCell(1).setCellValue(report.getRechargeAmount());
					row.createCell(2).setCellValue(report.getBonusAmount());
					row.createCell(3).setCellValue(report.getPresentAmount());
					row.createCell(4).setCellValue(report.getScore());
					row.createCell(5).setCellValue(report.getGold());
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
