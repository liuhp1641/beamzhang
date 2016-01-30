package com.cm.manage.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.quartz.IDistributionLockService;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.quartz.DistributionLockVO;

/**
 * 定时任务控制器
 *
 * @author
 */
@Controller
@RequestMapping("/quartzController")
public class QuartzController extends BaseController {

    private final Logger logger = Logger.getLogger(DictController.class);

    @Autowired
    private IDistributionLockService distributionLockService;

    /**
     * 跳转到彩种管理
     *
     * @return
     */
    @RequestMapping(params = "quartz")
    public String quartz() {
        return "/quartz/quartzList";
    }

    @RequestMapping(params = "quartzList")
    @ResponseBody
    public EasyuiDataGridJson list() {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        List<DistributionLockVO> distributionLockList = distributionLockService.getDistributionLockList();
        j.setRows(distributionLockList);
        return j;
    }

    @RequestMapping(params = "controlStatus")
    @ResponseBody
    public Json controlStatus(HttpServletRequest request, String name, Integer status) {
        int success = distributionLockService.updateStatus(name, status);
        Json j = new Json();
        if (success != 0) {
            String statusStr = "";
            if (status == 0) {
                statusStr = "正常";
            } else {
                statusStr = "锁定";
            }
            saveLog(request, "/quartzController.do?controlStatus", CommonConstants.LOG_FOR_SECURITY, name + "：" + statusStr);
            j.setSuccess(true);
        }
        return j;
    }

}
