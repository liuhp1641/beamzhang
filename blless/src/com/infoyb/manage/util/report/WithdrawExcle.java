package com.cm.manage.util.report;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cm.manage.vo.report.ReportRechargeVO;
import com.cm.manage.vo.report.ReportWithdrawVO;

public class WithdrawExcle {
	
	/***
	 * @describe 创建充值Excel数据
	 * @param drawMap
	 * @param workbook
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HSSFSheet buildWithdrawExcelData(Map<String,Object> drawMap,HSSFWorkbook workbook){
		String date1=(String) drawMap.get("date");
		HSSFSheet sheet = workbook.createSheet("充值");
		HSSFRow header0 = sheet.createRow(0);//第一行
		header0.createCell(0).setCellValue(date1+"#财务报表提现统计明细");
		HSSFRow header1 = sheet.createRow(1);//第二行
		header1.createCell(0).setCellValue("日期");
		header1.createCell(1).setCellValue("累计申请提现金额");
		header1.createCell(2).setCellValue("代扣用户手续费");
		header1.createCell(3).setCellValue("实际转账金额");
		
		List<ReportWithdrawVO> withdrawVOList = (List<ReportWithdrawVO>)drawMap.get("drawList");
		int rownum = 2;
		if(withdrawVOList != null && withdrawVOList.size() > 0){
			for(ReportWithdrawVO reportWithdrawVO : withdrawVOList){
				HSSFRow row = sheet.createRow(rownum++);
				row.createCell(0).setCellValue(reportWithdrawVO.getReportDate());
				row.createCell(1).setCellValue(reportWithdrawVO.getSumApplyWithdraw());
				row.createCell(2).setCellValue(reportWithdrawVO.getWithholdHandlingCharge());
				row.createCell(3).setCellValue(reportWithdrawVO.getActualTransferAmount());
			}
		}
		return sheet;
	}
}
