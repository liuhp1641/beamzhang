package com.cm.manage.controller.report;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.report.IReportRechargeService;
import com.cm.manage.vo.report.ReportRechargeVO;
/**
 * 财务报表 （充值统计）控制器
 *
 * @author
 */
@Controller
@RequestMapping("/reportRechargeController")
public class ReportRechargeController extends BaseController {
	private static Logger logger = Logger.getLogger(ReportRechargeController.class);
	
    @Autowired
    private IReportRechargeService reportRechargeService;

    /**
     * 初始化充值统计
     *
     * @return
     */
    @RequestMapping(params = "toReportRecharge")
    public String toReportRecharge(HttpServletRequest request, String userCode) {
        return "/report/reportRecharge";
    }
    
    /**
     * 充值统计查询
     *
     * @return
     */
    @RequestMapping(params = "reportRechargeByMonth")
    @ResponseBody
    public List<ReportRechargeVO> reportRechargeByMonth(String year,String month) {
        List<ReportRechargeVO> reportRecharge =  reportRechargeService.getReportRecharge(year,month);
        return reportRecharge;
    }

    /**
     * 充值统计明细查询
     *
     * @return
     */
    @RequestMapping(params = "reportRechargeDetail")
    @ResponseBody
    public List<ReportRechargeVO> reportRechargeDetail(String year,String month) {
        List<ReportRechargeVO> reportRecharge =  reportRechargeService.reportRechargeDetail(year,month);
        return reportRecharge;
    }
    
    /***
     * @describe 下载充值统计Excel数据文件
     * @param orderIdArr
     * @return
     */
    @RequestMapping(params = "downloadExcelFile")
    public String downloadExcelFile(String year,ModelMap mm,String month){
    	Map<String,Object> resMap = reportRechargeService.prepareExcelData(year,month);
    	mm.addAttribute("resMap", resMap);
    	mm.addAttribute("model", "recharge");
    	return "downloadCommonExcel";
    }
    
}
