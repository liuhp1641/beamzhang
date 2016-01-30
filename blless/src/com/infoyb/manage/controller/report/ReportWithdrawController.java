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
import com.cm.manage.service.report.IReportWithdrawService;
import com.cm.manage.vo.report.ReportWithdrawVO;
/**
 * 财务报表 （充值统计）控制器
 *
 * @author
 */
@Controller
@RequestMapping("/reportWithdrawController")
public class ReportWithdrawController extends BaseController {
	private static Logger logger = Logger.getLogger(ReportWithdrawController.class);
	
    @Autowired
    private IReportWithdrawService reportWithdrawService;

    /**
     * 初始化充值统计
     *
     * @return
     */
    @RequestMapping(params = "toReportWithdraw")
    public String toReportWithdraw(HttpServletRequest request, String userCode) {
        return "/report/reportWithdraw";
    }
    
    /**
     * 充值统计查询
     *
     * @return
     */
    @RequestMapping(params = "reportWithdrawByMonth")
    @ResponseBody
    public List<ReportWithdrawVO> reportWithdrawByMonth(String year,String month) {
        List<ReportWithdrawVO> reportWithdraw =  reportWithdrawService.getReportWithdraw(year,month);
        return reportWithdraw;
    }

    /**
     * 充值统计明细查询
     *
     * @return
     */
    @RequestMapping(params = "reportWithdrawDetail")
    @ResponseBody
    public List<ReportWithdrawVO> reportWithdrawDetail(String year,String month) {
        List<ReportWithdrawVO> reportWithdraw =  reportWithdrawService.reportWithdrawDetail(year,month);
        return reportWithdraw;
    }
    
    
    /***
     * @describe 下载充值统计Excel数据文件
     * @param year,month
     * @return
     */
    @RequestMapping(params = "downloadExcelFile")
    public String downloadExcelFile(String year,ModelMap mm,String month){
    	Map<String,Object> resMap = reportWithdrawService.prepareExcelData(year,month);
    	mm.addAttribute("resMap", resMap);
    	mm.addAttribute("model", "withdraw");
    	return "downloadCommonExcel";
    }
    
}
