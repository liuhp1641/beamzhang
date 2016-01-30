package com.cm.manage.view;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.report.RechargeExcle;
import com.cm.manage.util.report.WithdrawExcle;
import com.cm.manage.util.report.AccountReportExcel;
import com.cm.manage.util.report.BetReportExcel;
import com.cm.manage.util.report.TicketReportExcel;

public class CommonExcelView extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = new String(CommonBusiUtil.getExportNo().getBytes("iso8859-1"));
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName+".xls");

		Map<String,Object> resMap = (Map<String,Object>) model.get("resMap");
		String type=(String) model.get("model");
		if("bet".equals(type)){
			BetReportExcel.buildBetExcelData(resMap, workbook);
		}else if("ticket".equals(type)){
			TicketReportExcel.buildTicketExcelData(resMap, workbook);
		}else if("account".equals(type)){
			AccountReportExcel.buildAccountExcelData(resMap, workbook);
		}else if("recharge".equals(type)){
			RechargeExcle.buildRechargeExcelData(resMap, workbook);
		}else if("withdraw".equals(type)){
			WithdrawExcle.buildWithdrawExcelData(resMap, workbook);
		}else{
			Map<String,Object> drawMap = (Map<String,Object>) model.get("drawMap");
			CommonBusiUtil.buildDrawExcelData(drawMap,workbook);
		}
	}

}
