package com.cm.manage.controller.report;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.report.AccountReport;
import com.cm.manage.service.report.IAccountReportService;
@Controller
@RequestMapping("/accountReportController")
public class AccountReportController extends BaseController {
	private static Logger logger = Logger.getLogger(AccountReportController.class);
	
	@Autowired
	private IAccountReportService accountReportService;
	/**
	 * 账户统计
	 * @return
	 */
	@RequestMapping(params="toAccountReport")
	public String toAccountReport(){
		return "/report/accountReport";
	}
	/**
	 *账户按月统计列表
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(params="accountReportByMonth")
	@ResponseBody
	public List<AccountReport> accountReportByMonth(Integer year,Integer month,Integer memberType){
		return accountReportService.accountReportByMonth(year, month, memberType);
	}
	
	@RequestMapping(params="downloadExcelFile")
	public String downloadExcelFile(ModelMap mm,Integer reportYear,Integer reportMonth,String reportMember,String reportDatas){
		try{
		    JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(reportDatas);
		    List<AccountReport> list=new ArrayList<AccountReport>();
		    for (int i = 0; i < jsonArr.size(); i++) {
	            JSONObject jsonObj = jsonArr.getJSONObject(i);
	            AccountReport accountReport = (AccountReport) JSONObject.toBean(jsonObj, AccountReport.class);
	            list.add(accountReport);
	        }
	    	Map<String,Object> resMap = new HashMap<String, Object>();
	    	resMap.put("accountList", list);
	    	resMap.put("year", reportYear);
	    	resMap.put("month", reportMonth);
	    	resMap.put("memberType", reportMember);
	    	mm.addAttribute("resMap", resMap);
	    	mm.addAttribute("model", "account");
		}catch(Exception e){
			e.printStackTrace();
		}
	    	return "downloadCommonExcel";
	}
}
