package com.cm.manage.controller.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.sms.ISmsService;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.sms.MemberSms;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;

@Controller
@RequestMapping("/smsController")
public class SmsController extends BaseController {

    private static final Logger logger = Logger.getLogger(SmsController.class);

    @Autowired
    private ISmsService smsService;

    @RequestMapping(params = "sendPage")
    public String sendPage(HttpServletRequest request,String mobile) {
    	request.setAttribute("mobile", mobile);
        return "/sms/send";
    }

    @RequestMapping(params = "smsView")
    public String smsView() {
        return "/sms/view";
    }

    @RequestMapping(params = "smsDatagrid")
    @ResponseBody
    public EasyuiDataGridJson smsDatagrid(EasyuiDataGrid dg, MemberSms sms) {
        return smsService.smsDatagrid(dg, sms);
    }

    @RequestMapping(params = "sendSms")
    @ResponseBody
    public Json sendSms(HttpServletRequest request,HttpSession session, MemberSms sms) {
        Json j = new Json();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
            User u = sessionInfo.getUser();
            sms.setOperator(u.getName());
            smsService.sendSms(sms);
            String typeStr="";
            String type=sms.getType();
            if(type.equals("01")){
            	typeStr="手机号注册";
            }
            if(type.equals("02")){
            	typeStr="绑定手机";
            }
            if(type.equals("03")){
            	typeStr="找回密码";
            }
            saveLog(request, "/smsController.do?sendSms", CommonConstants.LOG_FOR_SMS, typeStr);
            j.setSuccess(true);
            j.setMsg("发送成功！");
        } else {
        	j.setSuccess(false);
            j.setMsg("您没有登录或登录超时，请重新登录后重试！");
        }
        return j;
    }

}
