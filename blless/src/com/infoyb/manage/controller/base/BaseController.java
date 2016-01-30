package com.cm.manage.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cm.manage.model.system.ManagesLog;
import com.cm.manage.service.base.IManageLogService;
import com.cm.manage.util.base.IpUtil;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.manage.util.base.CustomDateEditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 基础控制器，其他控制器需extends此控制器获得initBinder自动转换的功能
 *
 * @author
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {

    private static final Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    private IManageLogService manageLogService;

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        // binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    /**
     * 添加操作日志
     * @param request HttpServletRequest对象
     * @param action Controller中 RequestMapping 配置的参数
     * @param operatingType 操作类型
     * @param desc  操作描述
     */
    public void saveLog(HttpServletRequest request, String action, Integer operatingType, String desc) {
        HttpSession session = request.getSession();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
            User user = sessionInfo.getUser();
            ManagesLog manageLog = new ManagesLog();
            manageLog.setAdminName(user.getName());
            manageLog.setOperatingType(operatingType);
            manageLog.setType(action);
            manageLog.setIp(IpUtil.getIpAddr(request));
            manageLog.setCreateTime(new Date());
            manageLog.setDescription(desc);
            manageLogService.manageLogSave(manageLog);
        }
    }
}
