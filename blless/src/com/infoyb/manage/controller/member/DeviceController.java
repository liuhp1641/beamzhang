package com.cm.manage.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.member.IDeviceService;
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.member.Customer;

/**
 * 用户设备
 *
 * @author 
 */
@Controller
@RequestMapping("/deviceController")
public class DeviceController extends BaseController {

    private static final Logger logger = Logger.getLogger(DeviceController.class);

    @Autowired
    private IMemberService memberService;
    
    @Autowired
    private IDeviceService deviceService;

    @RequestMapping(params = "toDevice")
    public String toDevice(HttpServletRequest request) {
    	List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        return "/member/device";
    }
    /**
     * 设备列表
     * @param dg
     * @param customer
     * @param flag
     * @return
     */
    @RequestMapping(params = "deviceList")
    @ResponseBody
    public EasyuiDataGridJson deviceList(EasyuiDataGrid dg, Customer customer, boolean flag) {
        return deviceService.deviceList(dg, customer, flag);
    }
}
