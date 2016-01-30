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
import com.cm.manage.model.report.TicketReport;
import com.cm.manage.service.report.ITicketReportService;
@Controller
@RequestMapping("/ticketReportController")
public class TicketReportController extends BaseController {
	private static Logger logger = Logger.getLogger(TicketReportController.class);
	
	@Autowired
	private ITicketReportService ticketReportService;
	/**
	 * 出票统计
	 * @return
	 */
	@RequestMapping(params="toTicketReport")
	public String toTicketReport(){
		return "/report/ticketReport";
	}
	/**
	 * 出票按月统计列表
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(params="ticketReportByMonth")
	@ResponseBody
	public List<TicketReport> ticketReportByMonth(Integer year,Integer month,String postCode){
		return ticketReportService.ticketReportByMonth(year, month, postCode);
	}
	/**
	 * 统计每月明细
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(params="ticketReportDetail")
	@ResponseBody
	public List<TicketReport> ticketReportDetail(Integer year,Integer month,String postCode){
		return ticketReportService.ticketReportDetail(year, month, postCode);
	}
	/**
	 * 出票每月明细导出
	 * @param mm
	 * @return
	 */
	@RequestMapping(params="downloadExcelFile")
	public String downloadExcelFile(ModelMap mm,Integer year,Integer month,String postName,String dataStr){
		try{
		    dataStr=URLDecoder.decode(dataStr, "UTF-8");
		    JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(dataStr);
		    List<TicketReport> list=new ArrayList<TicketReport>();
		    for (int i = 0; i < jsonArr.size(); i++) {
	            JSONObject jsonObj = jsonArr.getJSONObject(i);
	            TicketReport ticketReport = (TicketReport) JSONObject.toBean(jsonObj, TicketReport.class);
	            list.add(ticketReport);
	        }
		    postName=URLDecoder.decode(postName, "UTF-8");
	    	Map<String,Object> resMap = new HashMap<String, Object>();
	    	resMap.put("ticketList", list);
	    	resMap.put("year", year);
	    	resMap.put("month", month);
	    	resMap.put("postName", postName);
	    	mm.addAttribute("resMap", resMap);
	      	mm.addAttribute("model", "ticket");
		}catch(Exception e){
			e.printStackTrace();
		}
	    	return "downloadCommonExcel";
	 }
}
