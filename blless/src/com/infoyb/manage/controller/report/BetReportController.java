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
import com.cm.manage.model.report.BetReport;
import com.cm.manage.service.report.IBetReportService;
@Controller
@RequestMapping("/betReportController")
public class BetReportController extends BaseController {
	private static Logger logger = Logger.getLogger(BetReportController.class);
	
	@Autowired
	private IBetReportService betReportService;
	/**
	 * 投注统计
	 * @return
	 */
	@RequestMapping(params="toBetReport")
	public String toBetReport(){
		return "/report/betReport";
	}
	/**
	 * 投注按月统计列表
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(params="betReportByMonth")
	@ResponseBody
	public List<BetReport> betReportByMonth(Integer year,Integer month){
		return betReportService.betReportByMonth(year, month);
	}
	/**
	 * 投注每月明细
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(params="betReportDetail")
	@ResponseBody
	public List<BetReport> betReportDetail(Integer year,Integer month){
		return betReportService.betReportDetail(year, month);
	}
	/**
	 * 投注每月明细导出
	 * @param mm
	 * @return
	 */
	@RequestMapping(params="downloadExcelFile")
	public String downloadExcelFile(ModelMap mm,Integer year,Integer month,String dataStr){
		try{
		    dataStr=URLDecoder.decode(dataStr, "UTF-8");
		    JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(dataStr);
		    List<BetReport> list=new ArrayList<BetReport>();
		    for (int i = 0; i < jsonArr.size(); i++) {
	            JSONObject jsonObj = jsonArr.getJSONObject(i);
	            BetReport betReport = (BetReport) JSONObject.toBean(jsonObj, BetReport.class);
	            list.add(betReport);
	        }
	    	Map<String,Object> resMap = new HashMap<String, Object>();
	    	resMap.put("betList", list);
	    	resMap.put("year", year);
	    	resMap.put("month", month);
	    	mm.addAttribute("resMap", resMap);
	    	mm.addAttribute("model", "bet");
		}catch(Exception e){
			e.printStackTrace();
		}
	    	return "downloadCommonExcel";
	 }
}
