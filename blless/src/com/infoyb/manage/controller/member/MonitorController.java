package com.cm.manage.controller.member;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.member.IMonitorService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.MonitorVO;

/**
 * 用户监控
 *
 * @author 
 */
@Controller
@RequestMapping("/monitorController")
public class MonitorController extends BaseController {

    private static final Logger logger = Logger.getLogger(MonitorController.class);
    
    @Autowired
    private IMonitorService monitorService;

    @RequestMapping(params = "toMonitor")
    public String toMonitor() {
        return "/member/monitor";
    }
    /**
     * 账户监控列表
     * @param dg
     * @param monitor
     * @return
     */
    @RequestMapping(params = "monitorList")
    @ResponseBody
    public EasyuiDataGridJson monitorList(EasyuiDataGrid dg, MonitorVO monitor)  {
        return monitorService.monitorList(dg, monitor);
    }
    /**
     * 跳转到IP监控
     * @return
     */
    @RequestMapping(params="toIpMonitor")
    public String toIpMonitor(){
    	return "/member/ipMonitor";
    }
    /**
     * ip监控列表
     * @param dg
     * @param monitor
     * @return
     */
    @RequestMapping(params="ipMonitorList")
    @ResponseBody
    public EasyuiDataGridJson ipMonitorList(EasyuiDataGrid dg, MonitorVO monitor){
    	return monitorService.ipMonitorList(dg, monitor);
    }
    /**
     * 跳转身份监控
     * @return
     */
    @RequestMapping(params="toCardCodeMonitor")
    public String toCardCodeMonitor(){
    	return "/member/cardCodeMonitor";
    }
    /**
     * 身份监控
     * @param dg
     * @param monitor
     * @return
     */
    @RequestMapping(params="cardCodeMonitorList")
    @ResponseBody
    public EasyuiDataGridJson cardCodeMonitorList(EasyuiDataGrid dg, MonitorVO monitor){
    	return monitorService.cardCodeMonitorList(dg, monitor);
    }
}
