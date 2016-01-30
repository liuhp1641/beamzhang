package com.cm.manage.util.report;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cm.manage.vo.report.ReportRechargeVO;

public class RechargeExcle {
	
	/***
	 * @describe 创建充值Excel数据
	 * @param drawMap
	 * @param workbook
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HSSFSheet buildRechargeExcelData(Map<String,Object> drawMap,HSSFWorkbook workbook){
		String date1=(String) drawMap.get("date");
		HSSFSheet sheet = workbook.createSheet("充值");
		HSSFRow header0 = sheet.createRow(0);//第一行
		header0.createCell(0).setCellValue(date1+"#财务报表充值统计明细");
		HSSFRow header1 = sheet.createRow(1);//第二行
		header1.createCell(0).setCellValue("日期");
		header1.createCell(1).setCellValue("累计投注金额");
		header1.createCell(2).setCellValue("支付宝");
		header1.createCell(3).setCellValue("百付宝");
		header1.createCell(4).setCellValue("连连支付");
		header1.createCell(5).setCellValue("神州付");
		header1.createCell(6).setCellValue("线下充值 ");
		
		List<ReportRechargeVO> rechargeVOList = (List<ReportRechargeVO>)drawMap.get("drawList");
		int rownum = 2;
		if(rechargeVOList != null && rechargeVOList.size() > 0){
			for(ReportRechargeVO reportRechargeVO : rechargeVOList){
				HSSFRow row = sheet.createRow(rownum++);
				row.createCell(0).setCellValue(reportRechargeVO.getReportDate());
				row.createCell(1).setCellValue(reportRechargeVO.getSumRecharge());
				row.createCell(2).setCellValue(reportRechargeVO.getZhiFuBao());
				row.createCell(3).setCellValue(reportRechargeVO.getBaiFuBao());
				row.createCell(4).setCellValue(reportRechargeVO.getLianLianZhifu());
				row.createCell(5).setCellValue(reportRechargeVO.getShenZhouFu());
				row.createCell(6).setCellValue(reportRechargeVO.getXianxiRecharge());
			}
		}
		return sheet;
	}
}
